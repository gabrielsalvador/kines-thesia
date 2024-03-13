package me.gabrielsalvador.pobject.components.controllers;

import controlP5.*;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PMessage;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CommandController extends Group {



    private final Component component;
    List<ControllerInterface<?>> children = new ArrayList<>();


    MultilineTextfield commandField = new MultilineTextfield(Sinesthesia.getInstance().getCP5(), "Enter command here");
    {
        commandField.getCaptionLabel().hide();
        commandField.addListenerFor(ControlP5Constants.ACTION_BROADCAST, event -> {
            compile();
        })
        .setAutoClear(false)
        .setHeight(100)
        ;
    }
    Textlabel feedbackLabel = new Textlabel(Sinesthesia.getInstance().getCP5(), "feedbackLabel");
    Button compileButton = new Button(Sinesthesia.getInstance().getCP5(), "Compile").addListenerFor(ControlP5Constants.ACTION_CLICK, event -> {
        compile();
    });


    public void compile() {



    }


    public CommandController(Component MessageComponent, ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        component = MessageComponent;

        children.add(commandField);
        children.add(feedbackLabel);
        children.add(compileButton);

        children.forEach(child -> {
            addChildVertically(child);
            addChildVertically(new Spacer(theControlP5, "spacer" + children.indexOf(child)));
            child.setWidth(getWidth());
        });

    }


    public void layoutDidFinish() {

        for (ControllerInterface child : children) {
            child.setWidth(getWidth());
        }
    }
}