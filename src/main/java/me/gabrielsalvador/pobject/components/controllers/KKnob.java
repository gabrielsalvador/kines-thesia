package me.gabrielsalvador.pobject.components.controllers;

import controlP5.*;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.sequencing.Clock;

public class KKnob extends Group  {
    Knob knob;

    public KKnob(PObjectProperty property, ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);

        knob = theControlP5.addKnob(theName)
                .setRange(40, 400)
                .setValue(50)
                .setPosition(0, 0)
                .setSize(100, 100)
                .setCaptionLabel(theName)
                .moveTo(this)
                .addListener(this);
        setHeight(20);
    }



    @Override
    public void controlEvent(ControlEvent theEvent) {
        System.out.println("Knob event: " + theEvent.getController().getValue());
        Clock.getInstance().setTempo((int) theEvent.getController().getValue());
    }


    @Override
    public Group setWidth(int theWidth) {
        super.setWidth(theWidth);
        knob.setWidth(theWidth);
        knob.setRadius((float) theWidth /2);

        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);
        knob.setHeight(theHeight);
        knob.setRadius((float) theHeight /2);
        return this;
    }




}
