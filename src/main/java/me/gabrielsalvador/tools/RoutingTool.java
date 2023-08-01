package me.gabrielsalvador.tools;


import me.gabrielsalvador.Config;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.*;
import processing.core.PGraphics;
import processing.event.KeyEvent;

public class RoutingTool extends Tool {

    RoutingSocket<?> start = null;

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public void onPressed(PObject pObject) {
        if(pObject instanceof RoutingSocket<?> routingSocket) {
            start = routingSocket;
        }
    }


    @Override
    public void onRelease(PObject pObject) {
        if(pObject instanceof RoutingSocket<?> ) {
            if(start != null) {
                RoutingSocket<?> routingSocket = (RoutingSocket<?>) pObject;
                if(start.getOwner() != routingSocket.getOwner()) {
                    RoutingConnection connection = new RoutingConnection(start, routingSocket);
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
           graphics.line(start.getPosition()[0], start.getPosition()[1], mouse[0], mouse[1]);
           graphics.popStyle();
       }
    }
}
