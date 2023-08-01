package me.gabrielsalvador.tools;

import controlP5.ControlWindow;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.*;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

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
        if(pObject instanceof RoutingSocket<?> routingSocket) {
            if(start != null) {
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
           int[] mouse = AppController.getCanvas().getMousePosition();
           graphics.line(start.getPosition()[0], start.getPosition()[1], mouse[0], mouse[1]);
       }
    }
}
