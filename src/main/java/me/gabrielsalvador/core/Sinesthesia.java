package me.gabrielsalvador.core;

import me.gabrielsalvador.input.InputManager;
import me.gabrielsalvador.view.ViewManager;
import processing.core.PApplet;

import java.util.HashMap;

public class Sinesthesia extends PApplet{

    private final StateManager _stateManager;
    private final InputManager _inputManager;
    private final ToolManager _toolManager;
    private final ViewManager _viewManager;

    /*
    *   Globals is a map that holds references to all the managers and objects that are needed throughout the application
    */
    private static final HashMap<Class<?>, Object> _globals = new HashMap<>();

    public Sinesthesia() {
        super();
        _stateManager = new StateManager();
        _inputManager = new InputManager();
        _toolManager = new ToolManager();
        _viewManager = new ViewManager();
        _globals.put(StateManager.class, _stateManager);
        _globals.put(InputManager.class, _inputManager);
        _globals.put(ToolManager.class, _toolManager);
        _globals.put(ViewManager.class, _viewManager);
    }
    public static void main(String[] args) {
        PApplet.main("me.gabrielsalvador.core.Sinesthesia");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        background(0);
    }

    public void draw() {


    }


    public <T> T get(Class<T> type) {
        return type.cast(_globals.get(type));
    }
    public StateManager getStateManager() {
        return _stateManager;
    }

    public InputManager getInputManager() {
        return _inputManager;
    }
    public ToolManager getToolManager() {
        return _toolManager;
    }
    public ViewManager getViewManager() {
        return _viewManager;
    }
}
