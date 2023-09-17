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
    private Vec2 _selectionStart = null;
    private Vec2 _selectionEnd = null;
    private boolean _dragging = false;
    private Vec2 _initialDragPosition = null;

    public SelectTool() {
        _cp5 = Sinesthesia.getInstance().getCP5();
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {
//        System.out.println("Selecting object");
//        if (pObject != null) {
//            clearSelection();
//            pObject.setIsSelected(true);

//            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
//        } else {
//            clearSelection();  // This will deselect everything when you click on an empty space
//        }
    }

    @Override
    public void onPressed(PObject pObject) {
        if (pObject != null && selectedObjects.contains(pObject)) {
            _dragging = true;  // Start dragging if pressing on an already selected object
        } else {
            clearSelection();
            if (pObject != null) {

                selectedObjects.add(pObject);
                pObject.setIsSelected(true);
            }
            // Start the selection square at the mouse position
            _selectionStart = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
        }
        if (_dragging) {
            _initialDragPosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
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
        if (_dragging) {
            // Calculate the displacement based on the initial drag position and the current mouse position
            Vec2 currentMousePosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
            Vec2 displacement = currentMousePosition.sub(_initialDragPosition);


            // Move all selected objects by the difference between their current position and the mouse position
            for (PObject selected : selectedObjects) {

                selected.getBodyComponent().moveByPixels(displacement);
            }

            _initialDragPosition = currentMousePosition;

        } else {
            // Update the selection square's end position
            _selectionEnd = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
            checkForObjectsInsideSelection();
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
        }
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
        if (_dragging) {
            _dragging = false;
        } else {
            _selectionStart = null;
            _selectionEnd = null;
        }
    }

    private void clearSelection() {
        for (PObject p : selectedObjects) {
            p.setIsSelected(false);
        }
        selectedObjects.clear();
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
    }

    private void checkForObjectsInsideSelection() {
        // Iterate over all objects and check if they are inside the selection rectangle
        for (PObject p : AppState.getInstance().getPObjects()) {
            Vec2 position = p.getBodyComponent().getPixelPosition();
            if (MathUtils.isInsideRectangle(position, _selectionStart, _selectionEnd) && !selectedObjects.contains(p)) {
                p.setIsSelected(true);
                selectedObjects.add(p);
            }
        }
    }
}
