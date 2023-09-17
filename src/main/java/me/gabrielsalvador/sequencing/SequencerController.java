package me.gabrielsalvador.sequencing;

import controlP5.*;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PKeyboard;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.Inlet;
import processing.core.PApplet;

import java.util.ArrayList;

public class SequencerController extends Controller<SequencerController> implements Device{
    public static final int DIVISION_TIME = 32 ;
    public static final int DIVISION_PITCH = 12;
    private int playhead = 0;
    protected boolean isPressed;
    protected int currentX = -1;
    protected int currentY = -1;
    protected int _myMode = MULTIPLES;
    private final SequencerState _sequencerState;

    /* PObject with inlets where he can send note events */
    private final ArrayList<Inlet> _connectedPObject = new ArrayList<Inlet>();

    public SequencerController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        setView(new SequencerView(this));
        Clock.getInstance().addDevice(this);
        _sequencerState = AppState.getInstance().getSequencerState();

    }

    @Override
    public void clockTick() {
        playhead = (playhead + 1) % DIVISION_TIME;
        for (int pitch = 0; pitch < DIVISION_PITCH; pitch++) {
            if (_sequencerState.getSteps()[playhead][pitch]) {
                sendNoteEvent(playhead, pitch);
            }
        }
    }
    private void sendNoteEvent(int time, int pitch) {
        /* Send note event to all connected PObjects */
        for (Inlet inlet : _connectedPObject) {
            inlet.receive("%d,%d".formatted(time,pitch));
        }
    }

    @Override
    public SequencerController updateInternalEvents(PApplet theApplet) {
        setIsInside(inside());

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
        _connectedPObject.add(inlet);
    }
    public void unregisterPObject(Inlet inlet){
        _connectedPObject.remove(inlet);
    }

    public int getDivisionTime() {
        return DIVISION_TIME;
    }

    public int getDivisionPitch() {
        return DIVISION_PITCH;
    }
    public boolean[][] getSteps() {
        return _sequencerState.getSteps();
    }
    public int getPlayhead(){
        return playhead;
    }


}
