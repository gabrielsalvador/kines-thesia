package me.gabrielsalvador.midi;
import me.gabrielsalvador.sequencing.Clock;
import themidibus.MidiBus;

import java.time.Duration;

public class MidiManager {

    private final MidiBus _midiBus;

    private static MidiManager _instance;

    private float _quantization = 1;  // 0 to 1
    private int _quantizationDivision = 32;

    private MidiManager() {
        _midiBus = new MidiBus(this, 1, 2);
    }

    public static synchronized MidiManager getInstance() {
        if (_instance == null) {
            _instance = new MidiManager();
        }

        return _instance;
    }

    public void sendNote(int midiNote, int velocity) {
        //create a new thread to send the midi note
        new Thread(() -> {
            _midiBus.sendNoteOn(1, midiNote, velocity);
            // System.out.println("Sending midi note " + midiNote + " with velocity " + velocity);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _midiBus.sendNoteOff(1, midiNote, velocity);
        }).start();
    }

    public void scheduleNote(int midiNote, int velocity) {

        long delayUntilNext16thNote = Clock.getInstance().getTimeUntilNextTick();

        // Schedule the note to be played at the next 16th note boundary
        Clock.getInstance().scheduleTask(() -> sendNote(midiNote, velocity), (long) (delayUntilNext16thNote * _quantization));
    }

    public MidiBus getMidiBus() {
        return _midiBus;
    }
}
