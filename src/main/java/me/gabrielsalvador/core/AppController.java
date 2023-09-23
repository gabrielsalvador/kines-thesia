package me.gabrielsalvador.core;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.views.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;


public class AppController {
    private static AppController _instance;
    private static AppState _appState;
    private static CanvasController _canvasController;
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    private final ConcurrentLinkedQueue<Runnable> _pObjectModificationsQueue = new ConcurrentLinkedQueue<Runnable>();
    private final InputManager _inputManager = InputManager.getInstance();

    private AppController() {
        _appState = AppState.getInstance();
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
    public PObject addPObject(PObject pObject) {
        Runnable modification = () -> {
            _appState.addPObject(pObject);
        };
        queueModification(modification);
        return pObject;
    }



    public ArrayList<View> getGizmos() {
        return _appState.getGizmos();
    }
    public void addGizmo(View gizmo) {
        ArrayList<View> gizmos = _appState.getGizmos();
        gizmos.add(gizmo);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
        _propertyChangeSupport.addPropertyChangeListener("selectedObjects", new PropertyChangeListener(){
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    if(evt.getNewValue() != null) {
                        ArrayList<PObject> selectedObjects = (ArrayList<PObject>) evt.getNewValue();
                        for (PObject pObject : AppState.getInstance().getPObjects()) {
                            pObject.setIsSelected(selectedObjects.contains(pObject));
                        }
                    }
                }
            }
        );
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        _propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }


    public ConcurrentLinkedQueue<Runnable> getModificationsQueue() {
        return _pObjectModificationsQueue;
    }

    public void clearObjects() {
        AppState.getInstance().clearObjects();
    }

    public void AddRouting() {

    }



    public void queueModification(Runnable modification){
        _pObjectModificationsQueue.add(modification);
    }

    public void applyModifications() {
        Iterator<Runnable> iterator = _pObjectModificationsQueue.iterator();
        while (iterator.hasNext()) {
            Runnable mofidication = iterator.next();
            mofidication.run();
            iterator.remove();
        }
    }


    public PObject createPObject() {
        PObject pObject = new PObject();
        addPObject(pObject);
        return pObject;
    }
}
