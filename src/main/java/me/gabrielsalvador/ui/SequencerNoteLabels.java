package me.gabrielsalvador.ui;

import controlP5.ControlP5;
import controlP5.Controller;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.timing.SequencerController;
import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import processing.core.PGraphics;

/**
 * Used to display the pitch of the musical note left of the sequencer.
 */

public class SequencerNoteLabels extends Controller<SequencerNoteLabels> {

    SequencerController sequencer;
    public SequencerNoteLabels(ControlP5 theControlP5, String theName, SequencerController sequencer) {
        super(theControlP5, theName);
        this.sequencer = sequencer;
        setSize(sequencer.getWidth(), sequencer.getHeight());
    }


    @Override
    public void draw(PGraphics theGraphics) {
        theGraphics.pushMatrix();
        theGraphics.translate(x(this.position), y(this.position));
        theGraphics.stroke(255);
        theGraphics.fill(255);

        Scale mainScale = MidiManager.getInstance().getScale();


        float stepY = (float) sequencer.getHeight() / (float) sequencer.getDivisionPitch();
        for (int i = 0; i < sequencer.getDivisionPitch(); i++) {
            MusicalNote note = mainScale.doInterval(sequencer.getDivisionPitch() - i - 1 + sequencer.getOffset());
            theGraphics.text(note.toString(), 0, i * stepY + 10);
        }

        theGraphics.popMatrix();
    }
}
