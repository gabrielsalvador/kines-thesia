package app.kinesthesia.core;



import java.io.Serializable;
import java.util.ArrayList;

public class AppState implements Serializable {
    private static AppState _instance;

    private final SequencerState _sequencerState = new SequencerState();

    private final ArrayList<PObject> _pObjects = new ArrayList<PObject>();

    private AppState() {}


    public static synchronized AppState getInstance() {
        if (_instance == null) {
            _instance = new AppState();
        }

        return _instance;
    }


    
    public ArrayList<PObject> getPObjects() {
        return _pObjects;
    }

    public void addPObject(PObject pObject) {
        _pObjects.add(pObject);
    }

    public void clearObjects() {
        _pObjects.clear();
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
