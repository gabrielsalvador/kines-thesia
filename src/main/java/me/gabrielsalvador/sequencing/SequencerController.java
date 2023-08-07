package me.gabrielsalvador.sequencing;

import controlP5.*;

public class SequencerController extends Controller<SequencerController> implements Device{
    private final int divisionTime = 32;
    private final int divisionPitch = 12;
    private int playhead = 0;

    private final boolean[][] steps = new  boolean[divisionTime][divisionPitch];
    public SequencerController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        setView( (ControllerView) new SequencerView( (SequencerController) this));
    }


    @Override
    public void clockTick() {

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
