package me.gabrielsalvador.tools;

import java.util.ArrayList;

import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.event.KeyEvent;

public class SelectTool extends Tool {

    private ControlP5 _cp5;
    private ArrayList<PObject> selectedObjects = new ArrayList<PObject>();
    private boolean isCloning = false; // Keep track of whether the object is being cloned
    private PObject clonedObject = null; // Reference to the cloned object


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
            if(!pObject.getIsSelected()){
                selectedObjects.clear();
            }
            selectedObjects.add(pObject);
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);

        }else{
            selectedObjects.clear();
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
        }
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
        System.out.println(selectedObjects);
    }

    @Override
    public void onRelease(PObject pObject) {

        //add object and remove gizmo
        if (isCloning) {
            AppState.getInstance().addPObject(clonedObject);
            AppState.getInstance().getGizmos().remove(clonedObject.getView());
        }

        isCloning = false;
        clonedObject = null;

        if (pObject == null) {
            selectedObjects.clear();
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObjects);
        }

    }

}

