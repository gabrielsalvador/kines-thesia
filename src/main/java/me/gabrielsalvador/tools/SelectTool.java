package me.gabrielsalvador.tools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.Inlet;
import me.gabrielsalvador.pobject.routing.Outlet;
import me.gabrielsalvador.pobject.routing.PatchSocket;
import me.gabrielsalvador.pobject.routing.Patchcord;
import me.gabrielsalvador.views.View;
import processing.core.PVector;
import processing.event.KeyEvent;

public class SelectTool extends Tool {

    private ControlP5 _cp5;
    private PVector startPoint = null;
    private PVector endPoint = null;
    private PatchSocket startSocket = null;  // Reference to the starting socket
    private PatchSocket endSocket = null;  // Reference to the ending socket

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
    public void onClick(int x, int y) {

    }

    @Override
    public void onPressed(int x, int y) {
        ArrayList<PObject> pObjects = AppState.getInstance().getPObjects();
        selectedObject = null;
        for (PObject pObject : pObjects) {
            View<PObject> view = pObject.getView();
            if (view.isMouseOver(x, y)) {
                pObject.onPressed(x, y);
                pObject.setIsSelected(true);
                selectedObject = pObject;
                startPoint = new PVector(x, y);

                // Check if the object is a PatchSocket
                if (pObject instanceof PatchSocket) {
                    startSocket = (PatchSocket) pObject;
                    System.out.println("Start socket selected");
                }

                // Check if the meta key is down to decide whether to clone
                isCloning = _cp5.isAltDown();

                // ... rest of your code
            } else {
                pObject.setIsSelected(false);
            }
        }
        AppController.getInstance().firePropertyChange("selectedObjects", null, selectedObject);
    }


    @Override
    public void onDrag(int x, int y) {
        if (selectedObject != null && startPoint != null) {
            PVector currentPosition = new PVector(x, y);
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
    public void onRelease(int x, int y) {
        // Update the endSocket if a PatchSocket is dragged onto
        ArrayList<PObject> pObjects = AppState.getInstance().getPObjects();
        for (PObject pObject : pObjects) {
            View<PObject> view = pObject.getView();
            if (view.isMouseOver(x, y) && pObject instanceof PatchSocket) {
                endSocket = (PatchSocket) pObject;
                System.out.println("End socket selected");
            }
        }

        // If both startSocket and endSocket are valid, create a new Patchcord
        if (startSocket != null && endSocket != null) {
            if (startSocket.getOwner() instanceof Outlet<?> && endSocket.getOwner() instanceof Inlet<?>) {
                Outlet<?> outlet = (Outlet<?>) startSocket.getOwner();
                Inlet<?> inlet = (Inlet<?>) endSocket.getOwner();

                Patchcord newPatchcord = new Patchcord(outlet, inlet);
                AppState.getInstance().addPObject(newPatchcord);
                System.out.println("Created new patchcord");
            }


            // Reset startSocket and endSocket for the next drag operation
            startSocket = null;
            endSocket = null;
        }

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

