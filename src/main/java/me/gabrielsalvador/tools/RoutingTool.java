package me.gabrielsalvador.tools;

import controlP5.ControlWindow;
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
    public void onClick(int x, int y) {

    }

    @Override
    public void onPressed(int x, int y) {
        ArrayList<PObject> pObjects = AppState.getInstance().getPObjects();
        for (PObject pObject : pObjects) {
            if (pObject instanceof RoutingSocket<?>) {
                RoutingSocket<?> routingSocket = (RoutingSocket<?>) pObject;
                if (routingSocket.getView().isMouseOver(x, y)) {
                    start = routingSocket;
                    return;
                }
            }
        }
    }


    @Override
    public void onRelease(int x, int y) {
        ArrayList<PObject> pObjects = AppState.getInstance().getPObjects();
        List<RoutingConnection> connectionsToAdd = new ArrayList<>();

        for (PObject pObject : pObjects) {
            if (pObject instanceof RoutingSocket<?> routingSocket) {
                if (routingSocket.getView().isMouseOver(x, y) && start != null) {
                    RoutingConnection routingConnection = new RoutingConnection(start, routingSocket);
                    connectionsToAdd.add(routingConnection);
                    System.out.println("Added routing connection to the temp list");
                    break;
                }
            }
        }

        for (RoutingConnection connection : connectionsToAdd) {
            AppState.getInstance().addPObject(connection);
        }

        start = null;
    }


    @Override
    public void onDrag(int x, int y) {

    }

    @Override
    public void draw(PGraphics graphics) {
        System.out.println("Drawing routing connection");
        if (start != null) {
            float[] startPosition = start.getOwner().getPosition();
            ControlWindow.Pointer mouse = Sinesthesia.getInstance().getCP5().getPointer();
            graphics.pushStyle();
            graphics.stroke(255);
            graphics.strokeWeight(2);
            graphics.line(startPosition[0], startPosition[1], mouse.getX(), mouse.getY());
            graphics.popStyle();
        }
    }
}
