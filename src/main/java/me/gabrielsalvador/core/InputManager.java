package me.gabrielsalvador.core;


import java.util.HashMap;

import me.gabrielsalvador.sequencing.Clock;
import me.gabrielsalvador.tools.ToolManager;
import processing.event.KeyEvent;

public class InputManager {

    private static InputManager _instance;
    private static final ToolManager _toolManager = ToolManager.getInstance();

    private final HashMap<Character, Runnable> _keyMappings = new HashMap<>() {{
        put('W', () -> System.out.println("W key pressed: Moving up!"));
        put('A', () -> System.out.println("A key pressed: Moving left!"));
        put(' ',() -> Clock.getInstance().togglePlay());
    }};


    private InputManager() {

    }

    public static synchronized InputManager getInstance() {
        if (_instance == null) {
            _instance = new InputManager();
            Sinesthesia.getInstance().registerMethod("keyEvent", _instance);
        }

        return _instance;
    }

    public void keyEvent( KeyEvent event ) {
        if (event.getAction() == KeyEvent.PRESS) {
            if (_keyMappings.containsKey(event.getKey())) {
                _keyMappings.get(event.getKey()).run();
            }else {
                    _toolManager.keyEvent(event);

            }
        }
    }

 

}
