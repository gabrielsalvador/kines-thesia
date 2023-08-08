package me.gabrielsalvador.core;

import java.io.Serializable;
import java.util.ArrayList;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.tools.Tool;
import me.gabrielsalvador.pobject.views.View;

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
        for(int i = 0 ; i < _pObjects.size() ; i++){
            _pObjects.remove(i);
        }
    }
}
