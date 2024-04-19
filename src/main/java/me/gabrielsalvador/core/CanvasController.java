package me.gabrielsalvador.core;

import controlP5.*;
import controlP5.events.ReleasedOutsideListener;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.views.View;
import me.gabrielsalvador.tools.ToolManager;
import me.gabrielsalvador.pobject.views.CanvasView;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import me.gabrielsalvador.pobject.PObject;

import java.util.ArrayList;

// Custom controller class that extends Controller
public class CanvasController extends Controller<CanvasController> implements ReleasedOutsideListener {

    private PObject _currentlyHovering;
    private final ToolManager _toolManager;
    private final PhysicsManager _physicsManager;
    /* time elapsed since last frame */
    private long _lastTime = System.nanoTime();
    /* time accumulated since last physics step */

    /* rate at which physics simulation moves forward */
    private final float _timeStep = 1.0f / 60.0f;
    private int xOff = 0;
    private int yOff = 0;

    public Thread physicsThread;



    public CanvasController(ControlP5 cp5, String name) {
        super(cp5, name);
        _myControllerView = new CanvasView(this);
        _toolManager = ToolManager.getInstance();
        _physicsManager = PhysicsManager.getInstance();

        // Start the physics thread
        physicsThread = new Thread(this::runPhysics);
        physicsThread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("An exception has been captured");
            System.out.printf("Thread: %s\n", t.getId());
            System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
            e.printStackTrace(System.out);
        });
        physicsThread.start();
    }


    @Override
    public void onPress() {
        isActive = inside();
        setUserInteraction(isActive);
        // x and y are relative to the canvas

        //dispatch the event to the tool
        updateHoveredObject();
        _toolManager.getCurrentTool().onPressed(_currentlyHovering, getMousePosition());


        //hide the command textfield
        Textfield t = (Textfield) cp5.get("CommandTextfield");
        if (t != null) {
            t.hide();
            t.clear();
        }
    }


    @Override
    public void mouseReleasedOutside() {
        isActive = false;
    }

    private void updateHoveredObject() {
        int[] mousePosition = getMousePosition();
        int x = mousePosition[0];
        int y = mousePosition[1];
        ArrayList<PObject> snapshot = new ArrayList<>(AppState.getInstance().getPObjects());
        for (PObject pObject : snapshot) {
            for (Component component : pObject.getComponents().values()) {
                View<Component> view = component.getView();
                if (view == null) {
                    continue;
                }
                boolean isHovered = view.isMouseOver(x, y);
                if (isHovered) {
                    pObject.setIsHovered(isHovered, x, y);
                    _currentlyHovering = pObject;
                    return;
                }
            }
        }
        //if it reaches this point it means that no object is being hovered
        if (_currentlyHovering != null) {
            _currentlyHovering.setIsHovered(false, x, y);
            _currentlyHovering = null;
        }
    }

    @Override
    public void onMove() {
        updateHoveredObject();
    }

    @Override
    public void onDrag() {
        //the order of these two lines od code is important, if you update the hovered object first then if when you drag, you drag the mouse out of the object, the object will  stop being hovered mid drag
        int[] mousePosition = getMousePosition();
        _toolManager.getCurrentTool().onDrag(_currentlyHovering, mousePosition);
        updateHoveredObject();
    }

    @Override
    public void onRelease() {
        _toolManager.getCurrentTool().onRelease(_currentlyHovering);

    }


    @Override
    public void onClick() {
        _toolManager.getCurrentTool().onClick(_currentlyHovering);
    }

    @Override
    public void onScroll(int theRotationValue) {

    }

    @Override
    public void keyEvent(KeyEvent theKeyEvent) {

        if (theKeyEvent.getAction() == KeyEvent.PRESS) {
            if (theKeyEvent.getKey() == 112) { //p
                MidiManager mm = MidiManager.getInstance();
                int chord = mm.getChord();
                mm.setChord(chord + 1);
            } else if (theKeyEvent.getKey() == 111) { //o
                MidiManager mm = MidiManager.getInstance();
                int chord = mm.getChord();
                mm.setChord(chord - 1);
            }
        }
    }


    public int[] getMousePosition() {
        int x = cp5.getPointer().getX() - (int) absolutePosition[0];
        int y = cp5.getPointer().getY() - (int) absolutePosition[1];
        return new int[]{x, y};
    }

    private final float maxFrameTime = 1.0f / 15.0f;  // Limit frameTime to 1/15th of a second


    private void runPhysics() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (PhysicsManager.getInstance().physicsThreadLock) {
                try {

                    _physicsManager.step(_timeStep, 8, 3);

                    AppController.getInstance().applyModifications();

                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                Thread.sleep((long) (_timeStep * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void draw(PGraphics graphics) {
        long currentTime = System.nanoTime();

        _lastTime = currentTime;

        graphics.pushMatrix();
        graphics.translate(x(position), y(position));
        getView().display(graphics, this);
        ToolManager.getInstance().getCurrentTool().draw(graphics);
        graphics.popMatrix();
    }

    public void shutdown() {
        physicsThread.interrupt();
    }


    public void setXOff(int xOff) {
        this.xOff = xOff;
    }

    public void setYOff(int yOff) {
        this.yOff = yOff;
    }

    public int getXOff() {
        return xOff;
    }

    public int getYOff() {
        return yOff;
    }

    public PObject getCurrentlyHovering() {
        return _currentlyHovering;
    }
}
