package me.gabrielsalvador.core;


import java.util.HashMap;

import controlP5.ControlP5;
import me.gabrielsalvador.timing.Clock;
import me.gabrielsalvador.tools.ToolManager;
import processing.event.KeyEvent;

public class InputManager {

    private static InputManager _instance;
    private static final ToolManager _toolManager = ToolManager.getInstance();

    private final HashMap<Character, Runnable> _keyMappings = new HashMap<>() {{
        put('W', () -> System.out.println("W key pressed: Moving up!"));
        put('A', () -> System.out.println("A key pressed: Moving left!"));

    }};


    private InputManager() {

    }

    public static synchronized InputManager getInstance() {
        if (_instance == null) {
            _instance = new InputManager();
            App.getInstance().registerMethod("keyEvent", _instance);
        }

        // setup key mappings
        ControlP5 cp5 = App.getInstance().getCP5();
        cp5.mapKeyFor( ()-> Clock.getInstance().togglePlay(), ' ');



        return _instance;
    }

    public void keyEvent(KeyEvent event) {
        //prevent ESC key from closing the app
        if (event.getKey() == 27) {
            App.getInstance().key = 0;

        }

        if (event.getAction() == KeyEvent.PRESS) {
            if (_keyMappings.containsKey(event.getKey())) {
                _keyMappings.get(event.getKey()).run();
            } else {
                _toolManager.keyEvent(event);

            }
        }
    }


}
