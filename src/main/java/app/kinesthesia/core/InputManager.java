package app.kinesthesia.core;


import java.util.HashMap;

import app.kinesthesia.gui.processing.ProcessingGuiMain;
import controlP5.ControlP5;
import app.kinesthesia.gui.processing.tools.ToolManager;
import processing.event.KeyEvent;

public class InputManager {

    private static InputManager _instance;
    private static final ToolManager _toolManager = ToolManager.getInstance();

    private final HashMap<Character, Runnable> _keyMappings = new HashMap<>() {{
    }};


    private InputManager() {

    }

    public static synchronized InputManager getInstance() {
        if (_instance == null) {
            _instance = new InputManager();
            ProcessingGuiMain.getInstance().registerMethod("keyEvent", _instance);
        }

        // setup key mappings
        ControlP5 cp5 = ProcessingGuiMain.getInstance().getCP5();
        cp5.mapKeyFor( ()-> Clock.getInstance().togglePlay(), ' ');



        return _instance;
    }

    public void keyEvent(KeyEvent event) {
        //prevent ESC key from closing the app
        if (event.getKey() == 27) {
            ProcessingGuiMain.getInstance().key = 0;

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
