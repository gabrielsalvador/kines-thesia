package me.gabrielsalvador.sequencing;

import controlP5.*;
import processing.core.PApplet;

public class SequencerController extends Controller<SequencerController> implements Device{
    private final int divisionTime = 32;
    private final int divisionPitch = 12;
    private int playhead = 0;
    protected boolean isPressed;
    protected int currentX = -1;
    protected int currentY = -1;
    protected int _myMode = MULTIPLES;
    private final boolean[][] steps = new  boolean[divisionTime][divisionPitch];
    public SequencerController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        setView( (ControllerView) new SequencerView( (SequencerController) this));
        Clock.getInstance().addDevice(this);
    }


    @Override
    public void clockTick() {
        playhead = (playhead + 1) % divisionTime;

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
