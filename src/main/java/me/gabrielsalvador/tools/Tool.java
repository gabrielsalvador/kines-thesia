package me.gabrielsalvador.tools;

import me.gabrielsalvador.core.App;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.tools.gizmo.Gizmo;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Tool implements Serializable {
    public static final int MODE_NORMAL = 0;
    private String _name;
    private String _description;
    private final PApplet _papplet = App.getInstance();
    private final ArrayList<ToolMode> _modes = new ArrayList<>();
    private ToolMode _currentMode = null;
    protected ArrayList<Gizmo> _gizmos = new ArrayList<Gizmo>();

    {
        getModes().add(new ToolMode("Normal"));
        setCurrentMode(getModes().get(0));
    }

    public Tool() {


    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public abstract void keyEvent(KeyEvent keyEvent);

    public abstract void onClick(PObject pObject);

    public boolean onPressed(PObject pObject, int[] mousePosition) { // return true if should stop propagation

        for (Gizmo gizmo : _gizmos) {
            if (gizmo.isInside(mousePosition)) {
                gizmo.setMouseDown(true);
                gizmo.setInitialDragPosition(new Vec2(mousePosition[0], mousePosition[1]));
                gizmo.onPressed();
                return true;
            }
        }

        return false;
    }

    public void onRelease(PObject pObject) {

        for (Gizmo gizmo : _gizmos) {
            if (gizmo.getMouseIsDown() ){
                gizmo.setMouseDown(false);
                gizmo.setInitialDragPosition(null);
                System.out.println("gizmo released");
                gizmo.onRelease(pObject);
            }
        }
    }

    public void onDrag(PObject pObject, int[] mousePosition) {

        for (Gizmo gizmo : _gizmos) {
            if (gizmo.getMouseIsDown()) {
                if(!gizmo._isDragging){
                    gizmo._isDragging = true;
                    gizmo.onDragStart(pObject, mousePosition);
                }
                gizmo.onDrag(pObject, mousePosition);
            }
            return;
        }
    }

    public PImage getCursorIcon() {
        if (_currentMode != null) {
            return _currentMode.getCursorIcon();
        }
        return null;

    }

    public void draw(PGraphics graphics) {

        for (Gizmo gizmo : _gizmos) {
            gizmo.draw(graphics);
        }

        checkIfShouldSwitchMode();// running this in the draw loop is makes the modes set in a latch way, for now it's ok but later lets handle this in KeyEvents
        PImage cursorIcon = getCursorIcon();
        if (cursorIcon != null) {
            _papplet.cursor(cursorIcon, 0, 0);
        }
    }


    //checks input to see if we should switch mode
    private void checkIfShouldSwitchMode() {
        setCurrentMode(getModes().get(0));
        for (ToolMode mode : getModes()) {
            if (mode.shouldSwitchMode(App.getInstance())) {
                setCurrentMode(mode);
                break;
            }
        }
    }

    public ArrayList<ToolMode> getModes() {
        return _modes;
    }

    public Tool setCurrentMode(ToolMode mode) {
        _currentMode = mode;
        return this;
    }

    public ToolMode getCurrentMode() {
        return _currentMode;
    }

}
