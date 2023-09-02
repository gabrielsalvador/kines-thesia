package me.gabrielsalvador.tools;


import me.gabrielsalvador.Config;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.*;
import processing.core.PGraphics;
import processing.event.KeyEvent;

public class RoutingTool extends Tool {

    private PSocket<?> start = null;

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public void onPressed(PObject pObject) {
        if(pObject instanceof PSocket<?> routingSocket) {
            start = routingSocket;
        }
    }


    @Override
    public void onRelease(PObject pObject) {
        if(pObject instanceof PSocket<?>) {
            if(start != null) {
                PSocket<?> end = (PSocket<?>) pObject;
                if(start.getOwner() != end.getOwner()) {
                    RoutingConnection connection = new RoutingConnection(start, end);
                    AppState.getInstance().addPObject(connection);
                }
            }
        }
        start = null;
    }


    @Override
    public void onDrag(PObject pObject) {


    }

    @Override
    public void draw(PGraphics graphics) {
       if(start != null) {
           int[] mouse = AppController.getInstance().getCanvas().getMousePosition();
           graphics.pushStyle();
           graphics.stroke(Config.THEME_COLOR_ROUTING_CONNECTION);
           graphics.strokeWeight(2);
           graphics.line(start.getPixelPosition()[0], start.getPixelPosition()[1], mouse[0], mouse[1]);
           graphics.popStyle();
       }
    }

    public boolean isRouting() {
        return start != null;
    }
}
