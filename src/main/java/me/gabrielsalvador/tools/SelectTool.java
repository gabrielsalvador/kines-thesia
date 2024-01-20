package me.gabrielsalvador.tools;

import java.util.ArrayList;
import controlP5.ControlP5;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.PGroup;
import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.core.*;
import org.jbox2d.common.Vec2;
import me.gabrielsalvador.pobject.PObject;
import processing.core.PGraphics;
import processing.event.KeyEvent;



@DisplayName("Select")
public class SelectTool extends Tool {

    private final ControlP5 _cp5;
    private CanvasController _canvas;
    private final ArrayList<PObject> selectedObjects = new ArrayList<>();
    private final Vec2 _selectionStart = null;
    private final Vec2 _selectionEnd = null;
    private Vec2 _initialDragPosition = null;
    private boolean _isDragging = false;
    
    {
        getModes().get(0).setIcon(Config.SELECTTOOL_CURSOR_ARROW_ICON);
        getModes().add(new ToolMode("SelectMultiple").setIcon(Config.SELECTTOOL_CURSOR_ADD_ICON).setModifierKeys(KeyEvent.SHIFT));

        setCurrentMode(getModes().get(0));
    }

    public SelectTool() {
        _cp5 = Sinesthesia.getInstance().getCP5();   
        
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.PRESS) {
            //if backspace is pressed, delete selected objects
            if (keyEvent.getKeyCode() == 8) {
                System.out.println("backspace pressed");
                for (PObject p : selectedObjects) {
                    AppController.getInstance().queueModification(p::remove);
                }
            }
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
            clearSelection();
        }

        if (pObject != null) {
            if (!selectedObjects.contains(pObject)) {
                select(pObject);
            }
            _isDragging = selectedObjects.contains(pObject);
            _initialDragPosition = _isDragging ? new Vec2(mousePosition[0], mousePosition[1]) : null;
        } else {
            clearSelection();
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
        if (_isDragging && _initialDragPosition != null) {
            Vec2 currentDragPosition = new Vec2(mousePosition[0], mousePosition[1]);
            Vec2 dragDelta = currentDragPosition.sub(_initialDragPosition);

            for (PObject selectedObject : selectedObjects) {
                Vec2 currentPosition = selectedObject.getBodyComponent().getPixelPosition();
                selectedObject.getBodyComponent().setPixelPosition(currentPosition.add(dragDelta));
            }
            _initialDragPosition = currentDragPosition;
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
    }


    private void clearSelection() {
        for (PObject p : selectedObjects) {
            p.setIsSelected(false);
        }
        selectedObjects.clear();
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
    }


    private void select(PObject pObject) {
        if (pObject.getIsSelected()) {
            pObject.setIsSelected(false);
            selectedObjects.remove(pObject);
        } else {
            pObject.setIsSelected(true);
            selectedObjects.add(pObject);
        }
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);

        _gizmos.clear();
        if(!selectedObjects.isEmpty())
            _gizmos.add(new FreetransformGizmo(new PGroup(selectedObjects)));

    }





}
