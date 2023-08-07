package me.gabrielsalvador.sequencing;

import controlP5.*;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.Inlet;
import processing.core.PApplet;

import java.util.ArrayList;

public class SequencerController extends Controller<SequencerController> implements Device{
    private final int divisionTime = 32;
    private final int divisionPitch = 12;
    private int playhead = 0;
    protected boolean isPressed;
    protected int currentX = -1;
    protected int currentY = -1;
    protected int _myMode = MULTIPLES;
    private final boolean[][] steps = new  boolean[divisionTime][divisionPitch];

    /* PObject with inlets where he can send note events */
    private final ArrayList<Inlet> _connectedPObject = new ArrayList<Inlet>();

    public SequencerController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        setView( (ControllerView) new SequencerView( (SequencerController) this));
        Clock.getInstance().addDevice(this);
    }

    @Override
    public void clockTick() {
        playhead = (playhead + 1) % divisionTime;
        for (int pitch = 0; pitch < divisionPitch; pitch++) {
            if (steps[playhead][pitch]) {
                sendNoteEvent(playhead, pitch);
            }
        }
    }
    private void sendNoteEvent(int time, int pitch) {
        for (Inlet inlet : _connectedPObject) {
            // Assuming the Inlet class has a method to handle note events
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
                    boolean isMarkerActive = (getSteps()[tX][tY] == true) ? true : false;
                    switch (_myMode) {
                        default:
                        case (SINGLE_COLUMN):
                            for (int i = 0; i < getDivisionPitch(); i++) {
                                getSteps()[tX][i] = false;
                            }
                            getSteps()[tX][tY] = (!isMarkerActive) ? true : getSteps()[tX][tY];
                            break;
                        case (SINGLE_ROW):
                            for (int i = 0; i < getDivisionPitch(); i++) {
                                getSteps()[tX][i] = false;
                            }
                            getSteps()[tX][tY] = (!isMarkerActive) ? true : getSteps()[tX][tY];
                            break;
                        case (MULTIPLES):
                            getSteps()[tX][tY] = !getSteps()[tX][tY];
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
            System.out.println("inside");
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

    public int getDivisionTime() {
        return divisionTime;
    }

    public int getDivisionPitch() {
        return divisionPitch;
    }
    public boolean[][] getSteps() {
        return steps;
    }
    public int getPlayhead(){
        return playhead;
    }
}
