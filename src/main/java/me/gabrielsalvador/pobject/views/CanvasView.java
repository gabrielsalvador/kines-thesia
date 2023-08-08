package me.gabrielsalvador.pobject.views;


import java.util.ArrayList;

import controlP5.ControllerView;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.core.CanvasController;
import processing.core.PGraphics;


public class CanvasView implements ControllerView<CanvasController> {

    private ArrayList<PObject> pObjects;
    private ArrayList<View> gizmos;

    public CanvasView() {
        pObjects = AppState.getInstance().getPObjects();
        gizmos = AppState.getInstance().getGizmos();
    }


    @Override
    public void display(PGraphics graphics, CanvasController controller) {
      graphics.pushStyle();
      graphics.strokeWeight(1);
        

      if (controller.isActive()) {
        graphics.stroke(127);
      } else {
        graphics.stroke(0);
      }
      graphics.rect(0,0,controller.getWidth(),controller.getHeight());

      /*draw pobjects*/
        for (PObject pObject : pObjects) {
            pObject.getView().display(graphics);
        }
      graphics.popStyle();

        gizmos.forEach(gizmo -> gizmo.display(graphics));
    }
  }