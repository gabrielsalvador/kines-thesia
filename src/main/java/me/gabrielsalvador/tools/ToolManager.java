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

    public Tool getCurrentTool() {
        return _currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this._currentTool = currentTool;
    }

    public void keyEvent(KeyEvent event) {
        char key = Character.toLowerCase(event.getKey());

        //tool keyboard shortcuts
        if (keyMappings.containsKey(key)) {
            // Instantiate the tool class and set it as the current tool.
            try {
                Class<? extends Tool> toolClass = keyMappings.get(key);
                Tool newTool = toolClass.getDeclaredConstructor().newInstance();
                setCurrentTool(newTool);
            
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }

        }
        //send keyevent to tool
        _currentTool.keyEvent(event);

    }
}
