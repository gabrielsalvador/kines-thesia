package me.gabrielsalvador.pobject.views;


import java.util.ArrayList;

import controlP5.ControllerView;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.core.CanvasController;
import processing.core.PGraphics;


public class CanvasView implements ControllerView<CanvasController> {

    private final ArrayList<PObject> pObjects;



    /* Objects that are not part of the physics simulation , they are display only as aid for some features*/
    private final ArrayList<View> gizmos;

    public CanvasView() {
        pObjects = AppState.getInstance().getPObjects();
        gizmos = AppController.getInstance().getGizmos();
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