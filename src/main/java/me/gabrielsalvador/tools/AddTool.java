package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.PlayableNote;
import me.gabrielsalvador.pobject.components.BodyComponent;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

public class AddTool extends Tool {

    public AddTool() {
        
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }


    @Override
    public void onClick(PObject pObject) {




        
    }

    @Override
    public void onPressed(PObject pObject) {
        //add a pobject to the canvas
        PObject newPObject = new PlayableNote();
        //set the position of the new pobject to the mouse position
        ControlP5 cp5 = Sinesthesia.getInstance().getCP5();
        int x = cp5.getPointer().getX();
        int y = cp5.getPointer().getY();
        Vec2 position = new Vec2(x,y);

        newPObject.addComponent(BodyComponent.class,new PhysicsBodyComponent(newPObject));
        newPObject.getBodyComponent().setPosition(position);
        AppController.getInstance().addPObject(newPObject);
    }




    @Override
    public void onRelease(PObject pObject) {

    }


    @Override
    public void onDrag(PObject pObject) {

    }

    @Override
    public void draw(PGraphics graphics) {

    }


}
