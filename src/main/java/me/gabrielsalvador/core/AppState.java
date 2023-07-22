package me.gabrielsalvador.core;

import java.util.HashSet;
import java.util.Set;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.tools.Tool;

public class AppState {
    private static AppState _instance;

    private Tool _currentTool;
    private final Set<PObject> _pObjects = new HashSet<PObject>();

    private AppState() {}


    public static synchronized AppState getInstance() {
        if (_instance == null) {
            _instance = new AppState();
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
