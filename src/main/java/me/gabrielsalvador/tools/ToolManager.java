package me.gabrielsalvador.tools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import processing.event.KeyEvent;


public class ToolManager  {
    private static ToolManager _instance;
    private Tool _currentTool = new SelectTool();

    protected Set<Class<? extends Tool>> tools = new HashSet<>();
    protected Map<Character, Class<? extends Tool>> keyMappings = new HashMap<>();
    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);


    private ToolManager() {
        loadToolClasses();
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

    public void setCurrentTool(Tool tool) {
        Tool oldTool = this._currentTool;
        this._currentTool = tool;
        _propertyChangeSupport.firePropertyChange("currentTool", oldTool, tool);
    }

    public void setCurrentTool(Class<? extends Tool> tool) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Tool newTool = tool.getConstructor().newInstance();
        setCurrentTool(newTool);
    }
    private void loadToolClasses() {

        Reflections reflections = new Reflections("me.gabrielsalvador.tools");
        Set<Class<? extends Tool>> toolClasses = reflections.getSubTypesOf(Tool.class);

        for (Class<? extends Tool> toolClass : toolClasses) {
            if (!Modifier.isAbstract(toolClass.getModifiers())) { // Exclude abstract classes
                tools.add(toolClass);
                char key = getShortcutForTool(toolClass);
                keyMappings.put(key, toolClass);
            }
        }
    }

    private char getShortcutForTool(Class<? extends Tool> toolClass) {
        switch (toolClass.getSimpleName()) {
            case "SelectTool":
                return 's';
            case "MoveTool":
                return 'm';
            case "AddTool":
                return 'a';
            case "CommandTool":
                return 'n';
            default:
                return '\0';
        }

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
        //send KeyEvent to tool
        _currentTool.keyEvent(event);

    }

    public Set<Class<? extends Tool>> getTools() {
        return tools;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }


}
