package me.gabrielsalvador.tools;

import java.util.ArrayList;
import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.event.KeyEvent;

public class SelectTool extends Tool {

    private ControlP5 _cp5;
    private ArrayList<PObject> selectedObjects = new ArrayList<PObject>();
    private Vec2 _selectionStart = null;
    private Vec2 _selectionEnd = null;

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
        if (pObject != null) {
            if (!pObject.getIsSelected()) {
                selectedObjects.clear();
            }
            selectedObjects.add(pObject);

        } else {
            selectedObjects.clear();
            _selectionStart = new Vec2(AppController.getInstance().getCanvas().getMousePosition()[0], AppController.getInstance().getCanvas().getMousePosition()[1]);
        }
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
    }


    @Override
    public void onDrag(PObject pObject) {
        if (pObject != null) {
            for (PObject selectedObject : selectedObjects) {
                int[] mouse = AppController.getInstance().getCanvas().getMousePosition();
                selectedObject.getBodyComponent().setPixelPosition(new Vec2(mouse[0], mouse[1]));
            }
        } else {
            // Update selection end point
            _selectionEnd = new Vec2(AppController.getInstance().getCanvas().getMousePosition()[0], AppController.getInstance().getCanvas().getMousePosition()[1]);

            // Clear previous selections
            selectedObjects.clear();

            // Check if objects are within the selection rectangle
            for (PObject pObject1 : AppState.getInstance().getPObjects()) {
                Vec2 position = pObject1.getBodyComponent().getPixelPosition();
                if (position.x >= _selectionStart.x && position.x <= _selectionEnd.x &&
                        position.y >= _selectionStart.y && position.y <= _selectionEnd.y) {
                    selectedObjects.add(pObject1);
                }
            }

            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
        }
    }


    @Override
    public void draw(PGraphics graphics) {
        if (_selectionStart != null) {
            _selectionEnd = new Vec2(AppController.getInstance().getCanvas().getMousePosition()[0], AppController.getInstance().getCanvas().getMousePosition()[1]);
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
//            selectedObjects.clear();
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
        }
    }
}

