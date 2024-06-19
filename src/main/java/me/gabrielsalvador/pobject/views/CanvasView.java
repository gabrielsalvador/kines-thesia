package me.gabrielsalvador.pobject.views;


import java.util.ArrayList;
import java.util.List;

import controlP5.ControllerView;
import me.gabrielsalvador.core.App;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.core.CanvasController;
import processing.core.PGraphics;


public class CanvasView implements ControllerView<CanvasController> {

    private final ArrayList<PObject> pObjects;
    private final CanvasController controller;
    private final App app = App.getInstance();

    public CanvasView(CanvasController controller) {
        pObjects = AppState.getInstance().getPObjects();
        this.controller = controller;
    }

    public int DEBUG_X = 750;
    public int DEBUG_Y = 425;

    @Override
    public void display(PGraphics graphics, CanvasController controller) {
        graphics.pushStyle();
        graphics.strokeWeight(1);


        if (controller.isActive()) {
            graphics.stroke(127);
        } else {
            graphics.stroke(0);
        }
        graphics.rect(0, 0, controller.getWidth(), controller.getHeight());

        //debug
        graphics.fill(255,125f);
        graphics.text("FPS: " + Math.round(app.frameRate), DEBUG_X, DEBUG_Y);
        graphics.text("PObjects: " + pObjects.size(), DEBUG_X, DEBUG_Y + 20);
        graphics.text("MouseX: " + app.mouseX, DEBUG_X, DEBUG_Y + 40);
        graphics.text("MouseY: " + app.mouseY, DEBUG_X, DEBUG_Y + 60);



        graphics.popStyle();
        /*draw pobjects*/
        graphics.translate(controller.getXOff(), controller.getYOff());


        List<PObject> snapshot = new ArrayList<>(pObjects);
        for (PObject pObject : snapshot) {
            graphics.pushStyle();
            if(pObject.getIsSelected()) {
                graphics.stroke(255, 0, 0);
            } else {
                graphics.stroke(255);
            }
            pObject.display(graphics);
            graphics.popStyle();
        }


    }


  }