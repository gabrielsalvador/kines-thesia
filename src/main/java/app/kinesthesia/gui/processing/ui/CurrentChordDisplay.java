package app.kinesthesia.gui.processing.ui;

import app.kinesthesia.core.Kinesthesia;
import app.kinesthesia.core.MidiManager;
import app.kinesthesia.core.MusicalNote;
import controlP5.ControlP5;
import controlP5.Textlabel;
import app.kinesthesia.core.PObjectProperty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CurrentChordDisplay extends CustomGroup implements PropertyEditor{

    Textlabel nameLabel;
    Textlabel currentChordValue;
    public CurrentChordDisplay(ControlP5 cp5, String theName, ArrayList<PObjectProperty> properties) {

        super(cp5, "CurrentChordDisplay");


        nameLabel = cp5.addTextlabel("currentChordLabel")
                .setText("Current Chord: ")
                .moveTo(this);
        nameLabel.get().align(LEFT, CENTER).setPaddingX(0).setPaddingY(0);

        MusicalNote currentChordRoot = new MusicalNote(MidiManager.getInstance().getChord());
        currentChordValue = cp5.addTextlabel("currentChordValue")
                .setText(currentChordRoot.toString())
                .moveTo(this);




        //setup chord listener
        PropertyChangeSupport pcs = Kinesthesia.getInstance().getPropertyChangeSupport();
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
    public CurrentChordDisplay setWidth(int theWidth) {
        super.setWidth(theWidth);
        int labelX1 = 0;
        int labelWidth ;
        int valueLabelX ;
        int valueLabelWidth ;

        labelWidth = (int) (theWidth * 0.5);
        valueLabelWidth = (int) (theWidth * 0.5);
        valueLabelX = getWidth()/2;

        nameLabel.setPosition(labelX1, 0).setSize(labelWidth, getHeight());
        currentChordValue.setPosition((float) theWidth /2, 50);

        return this;
    }

    @Override
    public CurrentChordDisplay setHeight(int theHeight) {
        super.setHeight(theHeight);
        int labelY = 0;
        int valueY = 0;
        int labelHeight = theHeight;
        int valueHeight = theHeight;

        nameLabel.setPosition(0, labelY).setSize(getWidth()/2, labelHeight);
        currentChordValue.setPosition(getWidth()/2, valueY).setSize(getWidth()/2, valueHeight);

        return this;
    }

    @Override
    public void resize(int width, int height) {
        setWidth(width);
        setHeight(height);

    }

    @Override
    public int getHeightForInspector() {
        return 20;
    }
}
