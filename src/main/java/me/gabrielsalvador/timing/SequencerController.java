package me.gabrielsalvador.timing;

import controlP5.*;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.routing.Inlet;
import me.gabrielsalvador.utils.MusicalNote;
import processing.core.PApplet;

import java.util.ArrayList;

public class SequencerController extends Controller<SequencerController> implements Device{
    public static final int MAX_DIVISION_TIME = 32 ;
    public static final int MAX_DIVISION_PITCH = 24;
    private int timeDivisions = MAX_DIVISION_TIME;
    private int pitchDivision = MAX_DIVISION_PITCH;

    private int playhead = 0;
    protected boolean isPressed;
    protected int currentX = -1;
    protected int currentY = -1;
    protected int _myMode = MULTIPLES;
    private final SequencerState _sequencerState;
    private int _internalBeatCounter = 0;
    private final int _howManyTicksToAdvance = 8; // the clock sends 16 ticks per quarter note, to make it 16th notes, we need to receive X ticks to advance


    /* PObject with inlets where he can send note events */
    private final ArrayList<Inlet> _connectedPObjects = new ArrayList<Inlet>();

    public SequencerController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        setView(new SequencerView(this));

        _sequencerState = AppState.getInstance().getSequencerState();

    }

    @Override
    public void clockTick() { //gets called by the clock X times per beat (see clock class)

        _internalBeatCounter = (_internalBeatCounter + 1) % _howManyTicksToAdvance;

        if( _internalBeatCounter == 0){
            playhead = (playhead + 1) % timeDivisions;
            for (int pitch = 0; pitch < pitchDivision; pitch++) {
                if (_sequencerState.getSteps()[playhead][pitch]) {
                    sendNoteEvent(playhead, pitch);
                }
            }
        }

    }
    private void sendNoteEvent(int time, int pitch) {

        MidiManager.getInstance().setChord(pitch);
        System.out.println("Sending note event: " + pitch + " at time: " + time);
        /* Send note event to all connected PObjects */
        for (Inlet inlet : _connectedPObjects) {
            MusicalNote note = new MusicalNote(pitch);
            inlet.receive(note);
        }
    }

    @Override
    public SequencerController updateInternalEvents(PApplet theApplet) {
        setIsInside(inside());

        //Quick fix because absolute position is broken in ControlP5 when using nested controllers
        float[] absolutePosition = new float[]{0,0};
        ControllerInterface<?> iterator = this;
        while (!iterator.getParent().getName().equals("default")){
            absolutePosition[0] += iterator.getPosition()[0];
            absolutePosition[1] += iterator.getPosition()[1];
            iterator = iterator.getParent();
        }



        if (getIsInside()) {
            if (isPressed) {
                int tX = (int)(((theApplet.mouseX - x(absolutePosition)) * getDivisionTime()) / getWidth());
                int tY = (int)(((theApplet.mouseY - y(absolutePosition)) * getDivisionPitch()) / getHeight());

                if (tX != currentX || tY != currentY) {
                    tX = PApplet.min(PApplet.max(0, tX), getDivisionTime() - 1);
                    tY = PApplet.min(PApplet.max(0, tY), getDivisionPitch() - 1);
                    boolean isMarkerActive = getSteps()[tX][tY];
                    switch (_myMode) {
                        default:
                        case (SINGLE_COLUMN):
                        case (SINGLE_ROW):
                            for (int i = 0; i < getDivisionPitch(); i++) {
                                _sequencerState.setStep(tX, i, false);
                            }
                            _sequencerState.setStep(tX, tY, !isMarkerActive);
                            break;
                        case (MULTIPLES):
                            _sequencerState.setStep(tX, tY, !getSteps()[tX][tY]);
                            break;
                    }
                    currentX = tX;
                    currentY = tY;
                }
            }
        }

        return this;
    }


    public void mousePressed() {
        isActive = getIsInside();
        if (getIsInside()) {
            isPressed = true;
        }
    }
    protected void mouseReleasedOutside() {
        mouseReleased();
    }


    public void mouseReleased() {
        if (isActive) {
            isActive = false;
        }
        isPressed = false;
        currentX = -1;
        currentY = -1;
    }

    public void registerPObject(Inlet inlet){
        _connectedPObjects.add(inlet);
    }
    public void unregisterPObject(Inlet inlet){
        _connectedPObjects.remove(inlet);
    }

    public int getDivisionTime() {
        return timeDivisions;
    }
    public SequencerController setDivisionTime(int theValue) {
        timeDivisions = theValue;
        return this;
    }
    public int getDivisionPitch() {
        return pitchDivision;
    }
    public int setDivisionPitch(int theValue) {
        pitchDivision = theValue;
        return theValue;
    }
    public boolean[][] getSteps() {
        return _sequencerState.getSteps();
    }
    public int getPlayhead(){
        return playhead;
    }

    public SequencerState getState() {
        return _sequencerState;
    }


}
