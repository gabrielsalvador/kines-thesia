package me.gabrielsalvador.tools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


import controlP5.ControlP5;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.event.KeyEvent;

public class SelectTool extends Tool {

    private ControlP5 _cp5;
    private PVector startPoint = null;
    private PVector endPoint = null;
    private PObject selectedObject = null;
    private boolean isCloning = false; // Keep track of whether the object is being cloned
    private PObject clonedObject = null; // Reference to the cloned object
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

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
            selectedObject = pObject;
            int[] mousePosition = AppController.getCanvas().getMousePosition();
            startPoint = new PVector(mousePosition[0], mousePosition[1]);
            endPoint = new PVector(pObject.getPosition()[0], pObject.getPosition()[1]);
            AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObject);
        }
    }




    @Override
    public void onDrag(PObject pObject) {
        if (selectedObject != null && startPoint != null) {
            int[] position = AppController.getCanvas().getMousePosition();
            PVector currentPosition = new PVector(position[0], position[1]);
            PVector displacement = PVector.sub(currentPosition, startPoint);

            if (isCloning) {
                // Update the position of the cloned object
                float[] objPosition = clonedObject.getPosition();
                objPosition[0] += displacement.x;
                objPosition[1] += displacement.y;
                clonedObject.setPosition(objPosition);
            } else {
                // Update the position of the selected object
                float[] objPosition = selectedObject.getPosition();
                objPosition[0] += displacement.x;
                objPosition[1] += displacement.y;
                selectedObject.setPosition(objPosition);
            }

            startPoint = currentPosition;
        }
    }

    @Override
    public void draw(PGraphics graphics) {
        System.out.println(selectedObject);
    }

    @Override
    public void onRelease(PObject pObject) {

        //add object and remove gizmo
        if (isCloning) {
            AppState.getInstance().addPObject(clonedObject);
            AppState.getInstance().getGizmos().remove(clonedObject.getView());
        }


        startPoint = null;
        endPoint = null;
        selectedObject = null;
        isCloning = false;
        clonedObject = null;

    }


    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }
}

