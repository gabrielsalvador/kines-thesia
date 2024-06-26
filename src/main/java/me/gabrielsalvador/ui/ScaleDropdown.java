package me.gabrielsalvador.ui;

import controlP5.ControlP5;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.utils.Scale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class ScaleDropdown extends DropdownEditor implements PropertyEditor{


    public ScaleDropdown(ControlP5 theControlP5, String theName, ArrayList<PObjectProperty> properties) {
        super(theControlP5, theName);

        String[] scaleNames = Arrays.stream(MidiManager.scales).map(Scale::getName).toArray(String[]::new);
        setItems(scaleNames);

        setTextLabel("Scale");
        Scale value = (Scale) properties.get(0).getValue();
        if (value != null) {
            dropdown.getCaptionLabel().setText(String.valueOf(value.getName()));
        }

    }


    @Override
    public int getHeightForInspector() {
        return 30;
    }
}
