package me.gabrielsalvador.gui.processing.ui;

import controlP5.*;
import me.gabrielsalvador.core.PObjectProperty;

import java.util.ArrayList;


public class OnPulseCodeEditor extends CodeEditor implements PropertyEditor {


    public OnPulseCodeEditor(ControlP5 theControlP5, String theName, ArrayList<PObjectProperty> properties) {
        super(theControlP5, theName, properties);

        titleLabel.setValue("On Pulse Code");
    }


}