package me.gabrielsalvador.core;

import controlP5.*;
import controlP5.events.ReleasedOutsideListener;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.tools.ToolManager;
import me.gabrielsalvador.pobject.views.CanvasView;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import me.gabrielsalvador.pobject.PObject;

import java.util.concurrent.ConcurrentLinkedQueue;

// Custom controller class that extends Controller
public class CanvasController extends Controller<CanvasController> implements ReleasedOutsideListener {

    private PObject _currentlyHovering;
    private final ToolManager _toolManager;
    private final PhysicsManager _physicsManager;

    public CanvasController(ControlP5 cp5, String name) {
        super(cp5, name);
        _myControllerView = new CanvasView();
        _toolManager = ToolManager.getInstance();
        _physicsManager = PhysicsManager.getInstance();
    }


    @Override
    public void onPress() {
        isActive = inside();
        setUserInteraction(isActive);
        // x and y are relative to the canvas
        _toolManager.getCurrentTool().onPressed(_currentlyHovering);
    }

    @Override
    public void mousePressed() {
        super.mousePressed();
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
        for (PObject pObject : AppState.getInstance().getPObjects()) {
            boolean isHovered = pObject.getView().isMouseOver(x, y);
            if (isHovered) {
                pObject.setIsHovered(isHovered, x, y);
                _currentlyHovering = pObject;
                return;
            }
        }
        //if it reaches this point it means that no object is being hovered
        if(_currentlyHovering != null){
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
        _toolManager.getCurrentTool().onDrag(_currentlyHovering);
        updateHoveredObject();
    }

    @Override
    public void onRelease() {
        _toolManager.getCurrentTool().onRelease(_currentlyHovering);

    }


    @Override
    public void onClick() {

    }

    @Override
    public void onScroll(int theRotationValue) {

    }

    @Override
    public void keyEvent(KeyEvent theKeyEvent) {
        if (isUserInteraction && isActive && theKeyEvent.getAction() == KeyEvent.PRESS) {

            _toolManager.keyEvent(theKeyEvent);

        }

    }

    public int[] getMousePosition() {
        int x = cp5.getPointer().getX() - (int) absolutePosition[0];
        int y = cp5.getPointer().getY() - (int) absolutePosition[1];
        return new int[]{x, y};
    }

    @Override
    public void draw(PGraphics graphics) {

        graphics.pushMatrix();
        graphics.translate(x(position), y(position));
        getView().display(graphics, this);
        ToolManager.getInstance().getCurrentTool().draw(graphics);
        graphics.popMatrix();

        _physicsManager.step(1f/30.0f,8, 3);
        AppController.getInstance().applyModifications();



    }

}
