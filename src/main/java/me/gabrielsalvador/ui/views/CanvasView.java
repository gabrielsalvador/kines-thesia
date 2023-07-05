package me.gabrielsalvador.ui.views;



import java.util.Set;
import controlP5.ControllerView;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject.PObject;
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
    }
  }