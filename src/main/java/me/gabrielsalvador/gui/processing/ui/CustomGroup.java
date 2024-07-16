package me.gabrielsalvador.gui.processing.ui;

import controlP5.ControlP5;
import controlP5.Group;


public abstract class CustomGroup extends Group {

    //props is a list of possible configurations for the editor, if needed
    public CustomGroup(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);

        hideBar();
        getCaptionLabel().hide();
    }

    abstract public void resize(int width, int height);


   
}
