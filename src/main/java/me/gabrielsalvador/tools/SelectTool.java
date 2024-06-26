package me.gabrielsalvador.tools;

import java.util.ArrayList;
import controlP5.ControlP5;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.PGroup;
import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.core.*;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.tools.gizmo.FreetransformGizmo;
import me.gabrielsalvador.tools.gizmo.RoutingGizmo;
import org.jbox2d.common.Vec2;
import me.gabrielsalvador.pobject.PObject;
import processing.core.PGraphics;
import processing.event.KeyEvent;



@DisplayName("Select")
public class SelectTool extends Tool {

    private final ControlP5 _cp5;
    private CanvasController _canvas;
    private final ArrayList<PObject> selectedObjects = new ArrayList<>();
    private Vec2 _selectionStart = null;
    private Vec2 _selectionEnd = null;
    private Vec2 _initialDragPosition = null;
    private boolean _isDragging = false;
    
    {
        getModes().get(0).setIcon(Config.SELECTTOOL_CURSOR_ARROW_ICON);
        getModes().add(new ToolMode("SelectMultiple").setIcon(Config.SELECTTOOL_CURSOR_ADD_ICON).setModifierKeys(KeyEvent.SHIFT));

        setCurrentMode(getModes().get(0));
    }

    public SelectTool() {
        _cp5 = App.getInstance().getCP5();

        
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

        if(!getCanvas().isActive()) return;

        // 8 == backspace
        if (keyEvent.getKey() == 8 && keyEvent.getAction() == KeyEvent.PRESS) {
            for (PObject pObject : selectedObjects) {
                AppController.getInstance().queueModification(() -> {
                    AppController.getInstance().removePObjectImmediatly(pObject);
                });
            }
            clearSelection();
        }

    }
    

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public boolean onPressed(PObject pObject, int[] mousePosition) {
        if (super.onPressed(pObject, mousePosition)) {
            return true;
        }

        if(getCurrentMode().getName().equals("Normal")){

            if(pObject == null){
                clearSelection();
                _selectionStart = new Vec2(mousePosition[0], mousePosition[1]);
                _selectionEnd = new Vec2(mousePosition[0], mousePosition[1]);
            }
            else{
                if (!selectedObjects.contains(pObject)) {
                    clearSelection();
                    select(pObject);
                }
                _isDragging = selectedObjects.contains(pObject);
                _initialDragPosition = _isDragging ? new Vec2(mousePosition[0], mousePosition[1]) : null;
            }
        }
        return false;
    }


    private CanvasController getCanvas() {
        if (_canvas == null) {
            _canvas = (CanvasController) _cp5.getController("MainCanvas");
        }
        return _canvas;
    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {
        super.onDrag(pObject, mousePosition);


        //if dragging, move selected objects
        if (_isDragging && _initialDragPosition != null) {
            Vec2 currentDragPosition = new Vec2(mousePosition[0], mousePosition[1]);
            Vec2 dragDelta = currentDragPosition.sub(_initialDragPosition);

            for (PObject selectedObject : selectedObjects) {
                Vec2 currentPosition = selectedObject.getBodyComponent().getPixelPosition();
                AppController.getInstance().queueModification(() -> {
                    selectedObject.getBodyComponent().setPixelPosition(currentPosition.add(dragDelta));
                });
            }
            _initialDragPosition = currentDragPosition;
        }

        //if not dragging, draw selection square
        else if(_selectionStart != null){
            _selectionEnd = new Vec2(mousePosition[0], mousePosition[1]);

            //check if any objects are within the selection square

            Vec2 topLeft = new Vec2(Math.min(_selectionStart.x, _selectionEnd.x), Math.min(_selectionStart.y, _selectionEnd.y));
            Vec2 bottomRight = new Vec2(Math.max(_selectionStart.x, _selectionEnd.x), Math.max(_selectionStart.y, _selectionEnd.y));
            clearSelection();
            synchronized (PhysicsManager.getInstance().physicsThreadLock){
                for (PObject p : AppState.getInstance().getPObjects()) {
                    Vec2 position = p.getBodyComponent().getPixelPosition();
                    if (position.x > topLeft.x && position.x < bottomRight.x && position.y > topLeft.y && position.y < bottomRight.y) {
                        select(p);
                    }
                }
            }
        }
    }



    public void draw(PGraphics graphics) {
        super.draw(graphics);

        // Draw the selection square if it exists
        if (_selectionStart != null && _selectionEnd != null) {
            graphics.pushStyle();
            graphics.fill(255, 255, 255, 50);
            graphics.noStroke();
            graphics.rect(_selectionStart.x, _selectionStart.y, _selectionEnd.x - _selectionStart.x, _selectionEnd.y - _selectionStart.y);
            graphics.popStyle();
        }
    }


    @Override
    public void onRelease(PObject pObject) {
        super.onRelease(pObject);
        _isDragging = false;
        _initialDragPosition = null;
        _selectionEnd = null;
        _selectionStart = null;
    }


    private void clearSelection() {

        for (PObject p : AppState.getInstance().getPObjects()) {
            p.setIsSelected(false);
        }
        _gizmos.clear();
        selectedObjects.clear();

        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
    }


    private void select(PObject pObject) {

        if (pObject.getIsSelected()) {
            return;
        } else {
            pObject.setIsSelected(true);
            selectedObjects.add(pObject);
        }

        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);

        _gizmos.clear();
        if(!selectedObjects.isEmpty()) {
            //TODO: use group instead of get(0)
            PGroup selectedObjects = new PGroup(this.selectedObjects);
            if((selectedObjects.get(0).getBodyComponent() instanceof PhysicsBodyComponent)){
                _gizmos.add(new FreetransformGizmo(selectedObjects));
            }

//            if(selectedObjects.get(0).getBodyComponent() instanceof BodyComponent){
//                _gizmos.add(new RoutingGizmo(selectedObjects));
//            }


        }


    }





}
