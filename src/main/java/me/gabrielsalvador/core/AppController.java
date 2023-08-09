package me.gabrielsalvador.core;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsPObject;
import me.gabrielsalvador.pobject.PlayableNote;
import me.gabrielsalvador.pobject.routing.RoutingConnection;
import me.gabrielsalvador.pobject.routing.PSocket;
import me.gabrielsalvador.utils.Vector;
import org.jbox2d.common.Vec2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;


public class AppController {
    private static AppController _instance;
    private static CanvasController _canvasController;
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    private final ConcurrentLinkedQueue<PObject> _pObjectModificationsQueue = new ConcurrentLinkedQueue<PObject>();

    private AppController() {

    }

    public static synchronized AppController getInstance() {
        if (_instance == null) {
            _instance = new AppController();
        }

        return _instance;
    }



    public  CanvasController getCanvas(){
        if(_canvasController == null){
            _canvasController = (CanvasController) Sinesthesia.getInstance().getCP5().getController("MainCanvas");
        }
        return _canvasController;
    }
    public void addPObject(PObject pObject) {
        queueModification(pObject);
    }

    public PlayableNote addPlayableNote(Vector position) {
        PlayableNote note = new PlayableNote().setPosition(position);
        AppState.getInstance().addPObject(note);
        return note;
    }
    public PhysicsPObject addPhysicsNote(Vec2 position) {
        PhysicsPObject note = new PhysicsPObject(position);
        AppController.getInstance().addPObject(note);
        return note;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
        _propertyChangeSupport.addPropertyChangeListener("selectedObjects", new PropertyChangeListener(){
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    if(evt.getNewValue() != null) {
                        ArrayList<PObject> selectedObjects = (ArrayList<PObject>) evt.getNewValue();
                        for (PObject pObject : AppState.getInstance().getPObjects()) {
                            if(selectedObjects.contains(pObject)){
                                pObject.setIsSelected(true);
                        }else {
                                pObject.setIsSelected(false);
                            }
                        }
                    }
                }
            }
        );
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        _propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void queueModification(PObject modification){
        _pObjectModificationsQueue.add(modification);
    }
    public ConcurrentLinkedQueue<PObject> getModificationsQueue() {
        return _pObjectModificationsQueue;
    }

    public void clearObjects() {
        AppState.getInstance().clearObjects();
    }

    public void AddRouting(PSocket<?> start, PSocket<?> end) {
        RoutingConnection RoutingConnection = new RoutingConnection(start, end);
        AppState.getInstance().addPObject(RoutingConnection);
    }


    public void addRoutingSocket(PObject owner, PSocket<?> i) {
        AppState.getInstance().addPObject(i);
    }


    public void applyModifications() {
        Iterator<PObject> iterator = _pObjectModificationsQueue.iterator();
        while (iterator.hasNext()) {
            PObject obj = iterator.next();
            AppState.getInstance().addPObject(obj);
            iterator.remove();
        }
    }
}
