package me.gabrielsalvador.tools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;


import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PVector;
import processing.event.KeyEvent;

public class SelectTool extends Tool {

    private PVector startPoint = null;
    private PVector endPoint = null;
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public SelectTool() {

    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(int x, int y) {


        Set<PObject> pObjects = AppState.getInstance().getPObjects();
        for (PObject pObject : pObjects) {

            View<PObject> view = pObject.getView();
            if (view.isMouseOver(x, y)) {
                 pObject.setIsSelected(true);
                _propertyChangeSupport.firePropertyChange("selectedObject", null, pObject);

            } else {
                pObject.setIsSelected(false);
            }
        }
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }



}
