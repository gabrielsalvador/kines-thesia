package me.gabrielsalvador.gui.processing.ui;

import controlP5.ControlP5;
import me.gabrielsalvador.core.MidiManager;
import me.gabrielsalvador.core.PObjectProperty;
import me.gabrielsalvador.core.Scale;
import java.util.ArrayList;
import java.util.Arrays;

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
