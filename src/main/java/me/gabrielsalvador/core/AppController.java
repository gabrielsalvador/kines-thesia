package me.gabrielsalvador.core;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PlayableNote;
import me.gabrielsalvador.pobject.routing.RoutingConnection;
import me.gabrielsalvador.pobject.routing.RoutingSocket;
import me.gabrielsalvador.utils.Vector;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class AppController {
    private static AppController _instance;
    private static CanvasController _canvasController;
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);


    private AppController() {

    }

    public static synchronized AppController getInstance() {
        if (_instance == null) {
            _instance = new AppController();
        }

        return _instance;
    }

    public static CanvasController getCanvas(){
        if(_canvasController == null){
            _canvasController = (CanvasController) Sinesthesia.getInstance().getCP5().getController("MainCanvas");
        }
        return _canvasController;
    }
    public void addPObject(PObject pObject) {
        AppState.getInstance().addPObject(pObject);
    }

    public PlayableNote addPlayableNote(Vector position) {
        PlayableNote note = new PlayableNote().setPosition(position);
        AppState.getInstance().addPObject(note);
        return note;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        _propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }


    public void clearObjects() {
        AppState.getInstance().clearObjects();
    }

    public void AddRouting(RoutingSocket<?> start, RoutingSocket<?> end) {
        RoutingConnection RoutingConnection = new RoutingConnection(start, end);
        AppState.getInstance().addPObject(RoutingConnection);
    }


}
