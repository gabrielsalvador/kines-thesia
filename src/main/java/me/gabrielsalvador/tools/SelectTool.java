package me.gabrielsalvador.tools;

import java.util.ArrayList;
import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import me.gabrielsalvador.utils.MathUtils;



public class SelectTool extends Tool {

    private ControlP5 _cp5;
    private ArrayList<PObject> selectedObjects = new ArrayList<PObject>();
    private Vec2 _selectionStart = null;
    private Vec2 _selectionEnd = null;
    private CanvasController canvasController;

    public SelectTool() {
        _cp5 = Sinesthesia.getInstance().getCP5();
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {


    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public void onPressed(PObject pObject) {
        selectedObjects.clear();
        _selectionStart = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
    }


    @Override
    public void onDrag(PObject pObject) {
        _selectionEnd = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
        //get objects inside the selection

        for (PObject p : AppState.getInstance().getPObjects()) {
            Vec2 position = p.getBodyComponent().getPixelPosition();
            if (MathUtils.isInsideRectangle(position, _selectionStart, new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]))){
                p.setIsSelected(true);
                selectedObjects.add(p);
            }
        }
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);

    }


    @Override
    public void draw(PGraphics graphics) {
        if (_selectionStart != null && _selectionEnd != null) {

            graphics.fill(255, 255, 255, 50);
            graphics.noStroke();
            graphics.rect(_selectionStart.x, _selectionStart.y, _selectionEnd.x - _selectionStart.x, _selectionEnd.y - _selectionStart.y);
        }
    }


    @Override
    public void onRelease(PObject pObject) {
        if( pObject == null) {
            _selectionStart = null;
            _selectionEnd = null;
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
        }
    }

    public CanvasController getCanvas() {
        if (canvasController == null) {
            this.canvasController = (CanvasController) _cp5.get("MainCanvas");
        }
        return canvasController;
    }
}

