package me.gabrielsalvador.core;

import java.io.Serializable;
import java.util.ArrayList;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.sequencing.SequencerState;
import me.gabrielsalvador.tools.Tool;
import me.gabrielsalvador.pobject.views.View;

public class AppState implements Serializable {
    private static AppState _instance;
    private Tool _currentTool;
    private final SequencerState _sequencerState = new SequencerState();
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
        for (int i = _pObjects.size() - 1; i >= 0; i--) {
            _pObjects.get(i).remove();
            _pObjects.remove(i);
        }
    }

    public SequencerState getSequencerState() {
        return _sequencerState;
    }


    public void loadSequencerState(SequencerState sequencerState) {
        int timeSteps = sequencerState.getSteps().length;
        int pitchSteps = sequencerState.getSteps()[0].length;

        for (int x = 0; x < timeSteps; x++) {
            for (int y = 0; y < pitchSteps; y++) {
                _sequencerState.setStep(x, y, sequencerState.getSteps()[x][y]);
            }
        }
    }
}
