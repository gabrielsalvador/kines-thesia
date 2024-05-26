package me.gabrielsalvador.ui;

import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Textlabel;

import java.util.ArrayList;

public class MidiChannelEditor extends DropdownEditor{


    public MidiChannelEditor(ControlP5 theControlP5, String theName, ArrayList<Object> props) {
        super(theControlP5, theName,props );

        setDisplayLabel(true);
        setTextLabel("Midi Channel");
        setItems(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11", "12", "13", "14", "15", "16"});
        dropdown.close();
        dropdown.getCaptionLabel().setText("1");


    }
}
