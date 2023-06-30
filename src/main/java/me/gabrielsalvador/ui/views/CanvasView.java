package me.gabrielsalvador.ui.views;


import java.util.HashSet;
import java.util.Set;

import controlP5.ControllerView;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject;
import me.gabrielsalvador.ui.controllers.Canvas;
import processing.core.PGraphics;

public class CanvasView implements ControllerView<Canvas> {

    Set<PObject> pObjects;

    public CanvasView() {
        pObjects = AppState.getInstance().getPObjects();
    }


    @Override
    public void display(PGraphics graphics, Canvas controller) {
      graphics.pushStyle();
      graphics.strokeWeight(1);
        graphics.stroke(0);
      if (controller.isInside()) {
        graphics.fill(controller.getColor().getActive());
      } else {
        graphics.fill(255,255,255);
      }
      graphics.rect(0,0,controller.getWidth(),controller.getHeight());

      /*draw pobjects*/
        for (PObject pObject : pObjects) {
            pObject.getView().display(graphics);
        }
      graphics.popStyle();
    }
  }