package me.gabrielsalvador.ui;

import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Textlabel;

public class DropdownEditor extends CustomGroup {

    protected DropdownList dropdown;
    Textlabel label;
    Boolean displayLabel = true;

    //1st prop is the label
    //2nd prop is an array of strings (options)
    public DropdownEditor(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);


        label = new Textlabel(theControlP5, "label");
        label.moveTo(this);

        dropdown = theControlP5.addDropdownList(theName + "dropdown").moveTo(this);






    }



    public DropdownEditor setDisplayLabel(Boolean displayLabel) {
        this.displayLabel = displayLabel;
        return this;
    }

    public DropdownEditor setItems(String[] options) {
        dropdown.clear();
        dropdown.addItems(options);
        return this;
    }

    public DropdownEditor setTextLabel(String text) {
        label.setText(text);
        return this;
    }

    @Override
    public DropdownEditor setWidth(int width) {
        super.setWidth(width);
        int labelX1 = 0;
        int labelWidth = 0;
        int dropdownX = 0;
        int dropdownWidth = 0;

        if(displayLabel) {
            labelWidth = (int) (width * 0.5);
            dropdownWidth = (int) (width * 0.5);
            dropdownX = labelWidth;
        }

        label.setWidth(labelWidth).setPosition(labelX1, 0);
        dropdown.setWidth(dropdownWidth).setPosition(dropdownX, 0);

        return this;
    }

    @Override
    public DropdownEditor setHeight(int height) {
        super.setHeight(height);

        label.setHeight(25).setPosition     (label.getPosition()[0], 0);
        dropdown.setBarHeight(30).setPosition   (dropdown.getPosition()[0], 5).setHeight(500).bringToFront();

        return this;
    }

    @Override
    public void resize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }



}
