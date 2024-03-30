package me.gabrielsalvador.tools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import controlP5.MappableCommand;
import me.gabrielsalvador.core.App;
import org.reflections.Reflections;
import processing.core.PImage;
import processing.event.KeyEvent;


public class ToolManager  {
    private static ToolManager _instance;
    private final App _app;
    private final Stack<Tool> _toolHistory = new Stack<>();
    protected Set<Class<? extends Tool>> availableTools = new HashSet<>();

    private final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);


    private ToolManager() {
        _app = App.getInstance();
        loadToolClasses();
        _toolHistory.push(new SelectTool());
    }

    public static synchronized ToolManager getInstance() {
        if (_instance == null) {
            _instance = new ToolManager();
        }

        return _instance;
    }

    public Tool getCurrentTool() {
        return _toolHistory.peek();
    }


    public Tool selectTool(Class<? extends Tool> toolClass) {
        Tool oldTool = _toolHistory.peek();

        try {
            Tool newTool = toolClass.getDeclaredConstructor().newInstance();

            if(oldTool.getClass() == toolClass) { // Don't repeat the same tool. instantiate a new one
                //replace the tool
                _toolHistory.pop();
                _toolHistory.push(newTool);
            }else{
                _toolHistory.push(newTool);
            }

            _propertyChangeSupport.firePropertyChange("currentTool", oldTool, newTool);
            return newTool;

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void popTool() {
        if (_toolHistory.size() > 1) {
            Tool oldTool = _toolHistory.pop();
            Tool newTool = _toolHistory.peek();
            _propertyChangeSupport.firePropertyChange("currentTool", oldTool, newTool);
        }
    }

    private void loadToolClasses() {

        Reflections reflections = new Reflections("me.gabrielsalvador.tools");
        Set<Class<? extends Tool>> toolClasses = reflections.getSubTypesOf(Tool.class);

        for (Class<? extends Tool> toolClass : toolClasses) {
            if (!Modifier.isAbstract(toolClass.getModifiers())) { // Exclude abstract classes
                availableTools.add(toolClass);
                char key = getShortcutForTool(toolClass);

                _app.getCP5().mapKeyFor(new MappableCommand() {
                    @Override
                    public void keyEvent() {
                        selectTool(toolClass);
                    }
                }, key);


            }
        }

        // Sort the tools by their class name
        availableTools = availableTools.stream().sorted(Comparator.comparing(Class::getSimpleName)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public  char getShortcutForTool(Class<? extends Tool> toolClass) {
        switch (toolClass.getSimpleName()) {
            case "SelectTool":
                return 's';
            case "MoveTool":
                return 'm';
            case "AddTool":
                return 'a';
            case "CommandTool":
                return 'n';
            case "AddResonatorTool":
                return 'r';
            case "AddPEmitter":
                return 'e';
            default:
                return '\0';
        }

    }
    public void keyEvent(KeyEvent event) {

        //send KeyEvent to tool
        _toolHistory.peek().keyEvent(event);

    }

    public Set<Class<? extends Tool>> getTools() {
        return availableTools;
    }



    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        _propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }


    
}
