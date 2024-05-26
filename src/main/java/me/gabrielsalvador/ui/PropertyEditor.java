package me.gabrielsalvador.ui;

import controlP5.ControlP5;
import controlP5.Group;

import java.util.ArrayList;



public abstract class PropertyEditor extends Group {

    //props is a list of possible configurations for the editor, if needed
    public PropertyEditor(ControlP5 theControlP5, String theName, ArrayList<Object> props,ArrayList<Object> options) {
        super(theControlP5, theName);

        hideBar();
        getCaptionLabel().hide();
    }

    abstract public void resize(int width, int height);


   
}
