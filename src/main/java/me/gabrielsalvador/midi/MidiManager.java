package me.gabrielsalvador.midi;
import me.gabrielsalvador.sequencing.Clock;
import me.gabrielsalvador.utils.Interval;
import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import themidibus.MidiBus;

public class MidiManager {


    private int inputIndex = 1;
    private int outputIndex = 2;
    private  MidiBus _midiBus;
    private static MidiManager _instance;
    private float _quantization = 1;  // 0 to 1
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


    public void sendNote(int channel,int midiNote, int velocity) {
        //create a new thread to send the midi note
        new Thread(() -> {
            _midiBus.sendNoteOn(channel, midiNote, velocity);
            try {
                Thread.sleep(NOTE_DURATION_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _midiBus.sendNoteOff(channel, midiNote, velocity);
        }).start();
    }

    public void scheduleNote(int channel, int midiNote, int velocity) {

        long delayUntilNext16thNote = Clock.getInstance().getTimeUntilNextTick();

        // Schedule the note to be played at the next 16th note boundary
        Clock.getInstance().scheduleTask(() -> sendNote(channel,midiNote, velocity), (long) (delayUntilNext16thNote * _quantization));
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
