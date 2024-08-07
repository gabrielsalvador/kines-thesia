package app.kinesthesia.gui.processing.ui;

import app.kinesthesia.core.MidiManager;
import app.kinesthesia.core.MusicalNote;
import app.kinesthesia.core.Scale;
import app.kinesthesia.gui.processing.SequencerController;
import controlP5.*;
import app.kinesthesia.gui.processing.Config;

import java.util.Map;

public class SequencerGroup extends CustomGroup {

    SequencerNoteLabels _noteLabels;
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

        //sequencer
        _sequencerController = new SequencerController(theControlP5, Config.MAIN_SEQUENCER_NAME).setDivisionTime(4);
        _sequencerController.moveTo(this).getCaptionLabel().hide();
        _noteLabels = new SequencerNoteLabels(theControlP5, "noteLabels", _sequencerController).moveTo(this);


        _pitchDown = new Button(theControlP5, "stepsDown").moveTo(this).setLabel("▼");
        _pitchUp = new Button(theControlP5, "pitchUP").moveTo(this).setLabel("▲");

        _pitchDown.addListenerFor(ACTION_PRESS, controlEvent -> {
            Scale mainScale = MidiManager.getInstance().getScale();
            MusicalNote newNote = mainScale.doInterval(_sequencerController.getOffset() - 1);
            if (newNote.getPitch() >= 0) {
                _sequencerController.setOffset(_sequencerController.getOffset() - 1);
            }
        });

        _pitchUp.addListenerFor(ACTION_PRESS, controlEvent -> {
            Scale mainScale = MidiManager.getInstance().getScale();
            MusicalNote newNote = mainScale.doInterval(_sequencerController.getOffset() + 1);
            if (newNote.getPitch() < 127) {
                _sequencerController.setOffset(_sequencerController.getOffset() + 1);
            }
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
                .setLabel("4").addListener(controlEvent -> {
                    int key = (int) controlEvent.getValue();
                    Map<String, Object> item = _numSteps.getItem(key);
                    int numSteps = Integer.parseInt((String)item.get("name"));
                    if (item != null) {
                        _sequencerController.setDivisionTime(numSteps);
                    }
                });

        //clear
        _clear = new Button(theControlP5, "clear").moveTo(this).setLabel("Clear").addListenerFor(ACTION_PRESS, controlEvent -> {
            _sequencerController.getState().clear();
        });
    }

    @Override
    public Group setWidth(int theWidth) {
         super.setWidth(theWidth);
         _sequencerController.setPosition(15,getHeight());
        _sequencerController.setWidth(theWidth-85);

        //note labels
        _noteLabels.setPosition(0,getHeight());
        _noteLabels.setWidth(30);


        //pitch buttons
        _pitchUp.setPosition(theWidth-45, 0);
        _pitchUp.setWidth(50);
        _pitchDown.setPosition(theWidth-45, 20);
        _pitchDown.setWidth(50);

        //steps dropdown
        _numStepsLabel.setPosition(theWidth-45, 40);
        _numSteps.setPosition(theWidth-45, 60);
        _numSteps.setWidth(50);

        //clear button
        _clear.setPosition(theWidth-45, 80);
        _clear.setWidth(50);
        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);
        _sequencerController.setHeight(theHeight);

        //note labels
        _noteLabels.setHeight(theHeight);

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
