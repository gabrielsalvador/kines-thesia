package me.gabrielsalvador.midi;
import me.gabrielsalvador.timing.Clock;
import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import themidibus.MidiBus;

public class MidiManager {


    private final int inputIndex = 1;
    private int outputIndex = 2;
    private  MidiBus _midiBus;
    private static MidiManager _instance;
    private final float _quantization = 1;  // 0 to 1
    private Scale _key = Scale.HIRAJOSHI.setRoot(new MusicalNote("C3"));
    private int _chord = 0;
    private static final int NOTE_DURATION_MS = 100;


    private MidiManager() {
        _midiBus = new MidiBus(this, inputIndex, outputIndex);

        System.out.println("MidiManager initialized");
        String[] inputs = MidiBus.availableInputs();
        String[] outputs = MidiBus.availableOutputs();

        System.out.println("input = " + inputs[1] + " output = " + outputs[2] + " initialized");

        System.out.println("interfaces available: ");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("input " + i + " = " + inputs[i]);
        }
        for (int i = 0; i < outputs.length; i++) {
            System.out.println("output " + i + " = " + outputs[i]);
        }

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

    public Scale getKey() {
        return _key;
    }

    public void setKey(Scale key) {
        _key = key;

    }


    public void changeOutput(int value) {
        outputIndex = value;
        _midiBus = new MidiBus(this, inputIndex, outputIndex);
    }

    public int getChord() {
        return _chord;
    }

    public void setChord(int chord) {
        _chord = chord;
    }
}
