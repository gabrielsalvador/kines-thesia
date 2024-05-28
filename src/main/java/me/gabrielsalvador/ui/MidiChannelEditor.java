package me.gabrielsalvador.ui;

import controlP5.*;
import me.gabrielsalvador.pobject.PObjectProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MidiChannelEditor extends DropdownEditor{

ArrayList<PObjectProperty> props;
    public MidiChannelEditor(ControlP5 theControlP5, String theName, ArrayList<PObjectProperty> properties) {
        super(theControlP5, theName );

        setDisplayLabel(true);
        setTextLabel("Midi Channel");
        setItems(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11", "12", "13", "14", "15", "16"});
        dropdown.close();
        dropdown.setItemHeight(20);

        Set<Integer> values = getCurrentValues(properties);
        if (values.size() == 1) {
            dropdown.getCaptionLabel().setText(String.valueOf(values.iterator().next()));
        } else {
            dropdown.getCaptionLabel().setText("Multiple");
        }

        dropdown.addListenerFor(ControlP5Constants.ACTION_ENTER, event -> bringToFront());
        dropdown.addListenerFor(ControlP5Constants.ACTION_CLICK, this::setMidiChannel);
        this.props = properties;
    }

    public void setMidiChannel(CallbackEvent event) {
        int midiChannel = (int) event.getController().getValue() + 1;
        props.forEach(p -> {
            try {
                p.setValue(midiChannel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Set<Integer> getCurrentValues(ArrayList<PObjectProperty> properties) {
        Set<Integer> values = new HashSet<>();
        properties.forEach(p -> {
            if (!values.contains(p.getValue())) {
                values.add((Integer) p.getValue());
            }
        });
        return values;
    }
}
