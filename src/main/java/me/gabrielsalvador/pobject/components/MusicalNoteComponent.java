package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.pobject.PObjectProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class MusicalNoteComponent extends Component implements Serializable {


    @InspectableProperty
    private String _sampleName = "";

    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return null;
    }


}
