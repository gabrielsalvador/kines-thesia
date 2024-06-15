package me.gabrielsalvador.ui;

import controlP5.ControlP5;
import controlP5.Textlabel;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.utils.MusicalNote;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CurrentChordDisplay extends CustomGroup implements PropertyEditor{

    Textlabel currentChordLabel;
    Textlabel currentChordValue;
    public CurrentChordDisplay(ControlP5 cp5, String theName, ArrayList<PObjectProperty> properties) {

        super(cp5, "CurrentChordDisplay");


        currentChordLabel = cp5.addTextlabel("currentChordLabel")
                .setText("Current Chord: ")
                .moveTo(this);

        MusicalNote currentChordRoot = new MusicalNote(MidiManager.getInstance().getChord());
        currentChordValue = cp5.addTextlabel("currentChordValue")
                .setText(currentChordRoot.toString())
                .moveTo(this);




        //setup chord listener
        PropertyChangeSupport pcs = AppController.getInstance().getPropertyChangeSupport();
        PropertyChangeListener listener = pcs.getPropertyChangeListeners("currentChord").length > 0 ? pcs.getPropertyChangeListeners("currentChord")[0] : null;
        if (listener != null) {
            pcs.removePropertyChangeListener("currentChord", listener);
        }
        pcs.addPropertyChangeListener("currentChord", evt -> {
            int newChord = (int) evt.getNewValue();
            currentChordValue.setValue(new MusicalNote(newChord).toString());
        });

    }


    @Override
    public void resize(int width, int height) {
        int labelX1 = 0;
        int labelWidth = 0;
        int valueX = 0;
        int valueWidth = 0;

        labelWidth = (int) (width * 0.5);
        valueWidth = (int) (width * 0.5);
        valueX = labelWidth;

        currentChordLabel.setPosition(labelX1, 0).setSize(labelWidth, height);
        currentChordValue.setPosition(valueX, 0).setSize(valueWidth, height);

    }

    @Override
    public int getHeightForInspector() {
        return 15;
    }
}
