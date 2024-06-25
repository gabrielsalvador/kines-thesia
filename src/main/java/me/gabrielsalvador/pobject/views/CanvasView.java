package me.gabrielsalvador.pobject.views;


import java.util.ArrayList;
import java.util.List;

import controlP5.ControllerView;
import me.gabrielsalvador.core.App;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.pobject.PhysicsManager;
import processing.core.PGraphics;


public class CanvasView implements ControllerView<CanvasController> {

    private final ArrayList<PObject> pObjects;
    private final CanvasController controller;
    private final App app = App.getInstance();

    public CanvasView(CanvasController controller) {
        pObjects = AppState.getInstance().getPObjects();
        this.controller = controller;
    }

    public int DEBUG_X = 5;
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
        String[] debugTexts = {
                "FPS: " + Math.round(app.frameRate),
                "PObjects: " + pObjects.size(),
                "Physics FPS: " + PhysicsManager.getInstance().physicsFPS,
                "MouseX: " + app.mouseX,
                "MouseY: " + app.mouseY
        };

        for (int i = 0; i < debugTexts.length; i++) {
            graphics.text(debugTexts[i], DEBUG_X, DEBUG_Y + (20 * i));
        }


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