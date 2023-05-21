package me.gabrielsalvador.model;

import java.util.Set;

import me.gabrielsalvador.tool.Tool;

public class State {
    private static State _instance;

    private Tool _currentTool;
    private final Set<PObject> _pObjects = new java.util.HashSet<PObject>();



    private State() {}


    public static synchronized State getInstance() {
        if (_instance == null) {
            _instance = new State();
        }

        return _instance;
    }


    public Tool getCurrentTool() {
        return _currentTool;
    }


    public void setCurrentTool(Tool currentTool) {
        this._currentTool = currentTool;
    }

    
    public Set<PObject> getPObjects() {
        return _pObjects;
    }

    public void addPObject(PObject pObject) {
        _pObjects.add(pObject);
    }
}
