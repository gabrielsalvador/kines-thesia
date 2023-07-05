package me.gabrielsalvador.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class ToolManager {
    private static ToolManager _instance;
    private Tool _currentTool = new SelectTool();

    protected Map<Character, Class<? extends Tool>> keyMappings = Map.of(
            'n', CommandTool.class,
            PApplet.ENTER, AddTool.class);


    private ToolManager() {
    }

    public static synchronized ToolManager getInstance() {
        if (_instance == null) {
            _instance = new ToolManager();
        }

        return _instance;
    }

    public Tool get_currentTool() {
        return _currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this._currentTool = currentTool;
    }

    public void keyEvent(KeyEvent event) {
        char key = Character.toLowerCase(event.getKey());

        System.out.println("Key pressed: " + key);
        if (keyMappings.containsKey(key)) {
            
            // Instantiate the tool class and set it as the current tool.
            try {
                setCurrentTool(keyMappings.get(key).getDeclaredConstructor().newInstance());
                System.out.println("Tool set to " + _currentTool);
            
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        
            
        }
        
    }
}
