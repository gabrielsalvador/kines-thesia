package me.gabrielsalvador.ui;

import controlP5.*;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.timing.SequencerController;

public class SequencerGroup extends CustomGroup {

    SequencerController _sequencerController;
    Button _pitchUp;
    Button _pitchDown;

    //steps
    Textlabel _numStepsLabel;
    DropdownList _numSteps;


    //clear
    Button _clear;
    public SequencerGroup(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);

        _sequencerController = new SequencerController(theControlP5, Config.MAIN_SEQUENCER_NAME).setDivisionTime(4);
        _sequencerController.moveTo(this);

        _pitchDown = new Button(theControlP5, "stepsDown").moveTo(this).setLabel("▼");
        _pitchUp = new Button(theControlP5, "pitchUP").moveTo(this).setLabel("▲");

        _pitchDown.addListenerFor(ControlP5Constants.ACTION_PRESS, controlEvent -> {
            //
        });

        _pitchUp.addListenerFor(ControlP5Constants.ACTION_PRESS, controlEvent -> {
            //
        });

        _numStepsLabel = theControlP5.addTextlabel("numStepsLabel")
                .setText("Steps")
                .moveTo(this);
        _numSteps = theControlP5.addDropdownList("numSteps")
                .setPosition(0, 0)
                .setSize(100, 100)
                .setBarHeight(20)
                .setItemHeight(20)
                .addItems(new String[]{"4", "8", "16", "32"})
                .close()
                .moveTo(this)
                .setLabel("4");

        //clear
        _clear = new Button(theControlP5, "clear").moveTo(this).setLabel("Clear").addListenerFor(ControlP5Constants.ACTION_PRESS, controlEvent -> {
            _sequencerController.getState().clear();
        });
    }

    @Override
    public Group setWidth(int theWidth) {
         super.setWidth(theWidth);
        _sequencerController.setWidth(theWidth-50);

        //pitch
        _pitchUp.setPosition(theWidth-45, 0);
        _pitchUp.setWidth(50);
        _pitchDown.setPosition(theWidth-45, 20);
        _pitchDown.setWidth(50);

        //steps
        _numStepsLabel.setPosition(theWidth-45, 40);
        _numSteps.setPosition(theWidth-45, 60);
        _numSteps.setWidth(50);

        //clear
        _clear.setPosition(theWidth-45, 80);
        _clear.setWidth(50);
        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);
        _sequencerController.setHeight(theHeight);

        //clear
        _clear.setPosition(getWidth()-45, theHeight-20);
        return this;
    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public Group setSize(int theWidth, int theHeight) {
        setWidth(theWidth);
        setHeight(theHeight);
        return this;
    }
}
