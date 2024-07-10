package me.gabrielsalvador.core;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.views.View;
import me.gabrielsalvador.timing.Clock;
import me.gabrielsalvador.timing.SequencerController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import static me.gabrielsalvador.pobject.PObjectPreset.getPresetByName;


public class AppController {
    private static AppController _instance;
    private static AppState _appState;
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    private final ConcurrentLinkedQueue<Runnable> _modificationsQueue = new ConcurrentLinkedQueue<Runnable>();

    public static Thread.UncaughtExceptionHandler defaultExceptionHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread th, Throwable ex) {
            Clock.getInstance().pause();

            ex.printStackTrace();
            // Stop all threads here
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                if (t.getState() == Thread.State.RUNNABLE) {
                    t.interrupt();
                }
            }
        }
    };



    private AppController() {
        _appState = AppState.getInstance();
        Thread.setDefaultUncaughtExceptionHandler(defaultExceptionHandler);

    }

    public static synchronized AppController getInstance() {
        if (_instance == null) {
            _instance = new AppController();
        }

        return _instance;
    }



    public PObject addPObject(PObject pObject) {
        Runnable modification = () -> {
            _appState.addPObject(pObject);
        };
        queueModification(modification);
        return pObject;
    }
    public PObject addPObjectImmiadiately(PObject pObject) {
        _appState.addPObject(pObject);
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
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return _propertyChangeSupport;
    }


    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        _propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }


    public ConcurrentLinkedQueue<Runnable> getModificationsQueue() {
        return _modificationsQueue;
    }

    public void clearObjects() {
        AppState.getInstance().clearObjects();
        PhysicsManager.getInstance().clearWorld();
    }




    public Runnable queueModification(Runnable modification){
        _modificationsQueue.add(modification);
        return modification;
    }

    public void applyModifications() {
        Iterator<Runnable> iterator = _modificationsQueue.iterator();
        while (iterator.hasNext()) {
            Runnable mofidication = iterator.next();
            mofidication.run();
            iterator.remove();
        }
    }


    public PObject createPObject() {
        PObject pObject = new PObject();
        return pObject;
    }
   
    public void removePObjectImmediatly(PObject pObject) {

        //cleanup , for example remove the body from the physics world
        for(Component component : pObject.getComponents().values()){
            component.dispose();
        }
        _appState.getPObjects().remove(pObject);
    }





    public void createRouting(PObject firstObject, PObject secondObject) {
        RoutingComponent RCA = firstObject.getRoutingComponent();
        if(RCA == null){
            RCA = new RoutingComponent(firstObject);
            firstObject.addComponent(RoutingComponent.class, RCA);
        }
        RCA.setTarget(secondObject);
    }

    public ConcurrentLinkedQueue getModificationQueue() {
        return _modificationsQueue;
    }



    public void addDroplet() {
        PObject[] droplet = getPresetByName("droplet").create();
        for (PObject pObject : droplet) {
            addPObject(pObject);
        }

        addPObjectImmiadiately(droplet[0]);

    }


}
