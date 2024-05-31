package me.gabrielsalvador.ui;

import controlP5.Button;
import controlP5.ControlP5;
import controlP5.ControlP5Constants;
import controlP5.Group;
import me.gabrielsalvador.timing.SequencerController;

public class SequencerGroup extends CustomGroup {

    SequencerController _sequencerController;
    Button _pitchUp;
    Button _stepsDown;

    public SequencerGroup(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);

        _sequencerController = new SequencerController(theControlP5, "sequencerController");
        _sequencerController.moveTo(this);
        _stepsDown = new Button(theControlP5, "stepsDown").moveTo(this);
        _pitchUp = new Button(theControlP5, "stepsUp").moveTo(this).setLabel("≈ßœ®∂ƒ˚ˆ¨)

        _stepsDown.addListenerFor(ControlP5Constants.ACTION_PRESS, controlEvent -> {
            _sequencerController.setDivisionTime(_sequencerController.getDivisionTime() - 1);
        });

        _pitchUp.addListenerFor(ControlP5Constants.ACTION_PRESS, controlEvent -> {
            _sequencerController.setDivisionTime(_sequencerController.getDivisionTime() + 1);
        });
    }

    @Override
    public Group setWidth(int theWidth) {
         super.setWidth(theWidth);
        _sequencerController.setWidth(theWidth-50);
        _pitchUp.setPosition(theWidth-45, 0);
        _pitchUp.setWidth(50);
        _stepsDown.setPosition(theWidth-45, 20);
        _stepsDown.setWidth(50);

        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);
        _sequencerController.setHeight(theHeight);
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
