package me.gabrielsalvador.ui;

import controlP5.*;
import me.gabrielsalvador.core.App;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.utils.CallbackWrapper;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class OnPulseCodeEditor extends CodeEditor implements PropertyEditor {


    public OnPulseCodeEditor(ControlP5 theControlP5, String theName, ArrayList<PObjectProperty> properties) {
        super(theControlP5, theName, properties);

        titleLabel.setValue("On Pulse Code");
    }


}