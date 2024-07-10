package me.gabrielsalvador.core;


import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.PObject;

import java.util.ArrayList;

import static me.gabrielsalvador.core.AppController.defaultExceptionHandler;

// Custom controller class that extends Controller
public class CanvasController {

    private PObject _currentlyHovering;

    private final PhysicsManager _physicsManager;
    /* time elapsed since last frame */
    public long _lastTime = System.nanoTime();
    /* time accumulated since last physics step */

    /* rate at which physics simulation moves forward */
    private final float _timeStep = 1.0f / 60.0f;
    private int xOff = 0;
    private int yOff = 0;

    public Thread physicsThread;



    public CanvasController( ) {

        _physicsManager = PhysicsManager.getInstance();

        // Start the physics thread
        physicsThread = new Thread(this::runPhysics);
        physicsThread.setUncaughtExceptionHandler(defaultExceptionHandler);
        physicsThread.start();


    }



    private void runPhysics() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (PhysicsManager.getInstance().physicsThreadLock) {
                try {
                    _physicsManager.step(_timeStep, 8, 3);
                    AppController.getInstance().applyModifications();
                }catch (Exception e) {
                    defaultExceptionHandler.uncaughtException(Thread.currentThread(), e);
                }
            }
            try {
                Thread.sleep((long) (_timeStep * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();  // print the stack trace of the exception
                Thread.currentThread().interrupt();
            }
        }
    }


}
