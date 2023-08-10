package me.gabrielsalvador.tools;

import java.util.ArrayList;

import controlP5.ControlP5;
import controlP5.Pointer;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.views.View;
import processing.core.PGraphics;
import processing.event.KeyEvent;

public class SelectTool extends Tool {

    private ControlP5 _cp5;
    private ArrayList<PObject> selectedObjects = new ArrayList<PObject>();


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
        }
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
    }


    @Override
    public void onDrag(PObject pObject) {
        if (pObject != null) {
            for (PObject selectedObject : selectedObjects) {
                int[] mouse = AppController.getInstance().getCanvas().getMousePosition();
                float[] objPos = selectedObject.getPosition();
                selectedObject.setPosition(new float[]{mouse[0], mouse[1]});
            }
        }

    }

    @Override
    public void draw(PGraphics graphics) {


    }

    @Override
    public void onRelease(PObject pObject) {
        if (pObject != null) return;
            selectedObjects.clear();
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);

    }
}

