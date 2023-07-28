package me.gabrielsalvador.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.tools.Tool;
import me.gabrielsalvador.views.View;

public class AppState implements Serializable {
    private static AppState _instance;

    private Tool _currentTool;
    private final ArrayList<View> _gizmos = new ArrayList<View>();
    private final ArrayList<PObject> _pObjects = new ArrayList<PObject>();

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

    
    public ArrayList<PObject> getPObjects() {
        return _pObjects;
    }
    public ArrayList<View> getGizmos() {  return _gizmos;}

    public void addPObject(PObject pObject) {
        _pObjects.add(pObject);
    }

    public void clearObjects() {
        _pObjects.clear();
    }
}
