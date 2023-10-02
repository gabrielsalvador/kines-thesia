package me.gabrielsalvador.tools;

import java.util.ArrayList;

import controlP5.ControlP5;
import me.gabrielsalvador.core.*;
import me.gabrielsalvador.pobject.PObject;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import me.gabrielsalvador.utils.MathUtils;

public class SelectTool extends Tool {

    private final ControlP5 _cp5;
    private CanvasController _canvas;
    private final ArrayList<PObject> selectedObjects = new ArrayList<>();
    private final Vec2 _selectionStart = null;
    private final Vec2 _selectionEnd = null;
    private final boolean _dragging = false;
    private final Vec2 _initialDragPosition = null;

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
    public void onPressed(PObject pObject, int[] mousePosition) {
        if (pObject != null){
            pObject.setIsSelected(true);
        }
        else{
            clearSelection();
        }
    }

    private CanvasController getCanvas() {
        if (_canvas == null) {
            _canvas = (CanvasController) _cp5.getController("MainCanvas");
        }
        return _canvas;
    }

    @Override
    public void onDrag(PObject pObject) {

    }

    @Override
    public void draw(PGraphics graphics) {
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

    }

    private void clearSelection() {
        for (PObject p : selectedObjects) {
            p.setIsSelected(false);
        }
        selectedObjects.clear();
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
    }


}
