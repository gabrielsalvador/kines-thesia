package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class MusicalNoteComponent extends Component implements Serializable {


    @PObject.InspectableProperty
    private final String _sampleName = "";

    public MusicalNoteComponent(PObject owner) {
        super(owner);
    }

    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return "Musical Note Information";
    }

    public String getSampleName() {
        return _sampleName;
    }
}
