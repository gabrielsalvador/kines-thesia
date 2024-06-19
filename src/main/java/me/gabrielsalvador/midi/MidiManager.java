package me.gabrielsalvador.midi;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.HasPProperties;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.timing.Clock;
import me.gabrielsalvador.ui.CurrentChordDisplay;
import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import themidibus.MidiBus;

import java.util.ArrayList;

public class MidiManager implements HasPProperties {


    private final int inputIndex = 1;
    private int outputIndex = 2;
    private  MidiBus _midiBus;
    private static MidiManager _instance;
    private final float _quantization = 1;  // 0 to 1
    private Scale _scale = Scale.HIRAJOSHI.setRoot(new MusicalNote("C3"));
    private int _chord = 0;
    private static final int NOTE_DURATION_MS = 100;


    private MidiManager() {
        _midiBus = new MidiBus(this, inputIndex, outputIndex);

        System.out.println("MidiManager initialized");

    }

    public static synchronized MidiManager getInstance() {
        if (_instance == null) {
            _instance = new MidiManager();
        }

        return _instance;
    }



    public void scheduleNote(int channel, int midiNote, int velocity, long duration) {

        if (duration == -1) {
            duration = NOTE_DURATION_MS;
        }

        long delayUntilNext16thNote = Clock.getInstance().getTimeUntilNextTick();

        //delay until next 16th note
        long time = (long) (delayUntilNext16thNote * _quantization);
        // Schedule note on
        Clock.getInstance().scheduleTask(() -> _midiBus.sendNoteOn(channel, midiNote, velocity), time);
        // Schedule note off
        Clock.getInstance().scheduleTask(() -> _midiBus.sendNoteOff(channel, midiNote, velocity), time + duration*1000_000);

    }

    public MidiBus getMidiBus() {
        return _midiBus;
    }

    public Scale getScale() {
        return _scale;
    }

    public void setKey(Scale key) {
        _scale = key;

    }


    public void changeOutput(int value) {
        outputIndex = value;
        _midiBus = new MidiBus(this, inputIndex, outputIndex);
    }

    @PObject.InspectableProperty(displayName = "Chord",controllerClass = CurrentChordDisplay.class)
    public int getChord() {
        return _chord;
    }
//    @PObject.InspectableProperty.SetterFor("Chord")
    public void setChord(int chord) {
        AppController.getInstance().firePropertyChange("currentChord", _chord, chord);
        _chord = chord;
    }

    @Override
    public ArrayList<PObjectProperty> getProperties() {
        return PObjectProperty.getProperties(this);
    }
}
