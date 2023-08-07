package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PGraphics;

import java.io.Serial;

public class RoutingConnection extends PObject {

    private RoutingSocket<Inlet> _inlet;
    private RoutingSocket<Outlet> _outlet;

    public RoutingConnection(RoutingSocket<?> inlet, RoutingSocket<?> outlet) {
        super();
        _inlet = (RoutingSocket<Inlet>) inlet;
        _outlet = (RoutingSocket<Outlet>) outlet;
        setView(new RoutingConnectionView(this));
    }
    @Override
    public void onEnter(int x, int y) {

    }

    @Override
    public void onLeave(int x, int y) {

    }

    @Serial
    private void readObject(java.io.ObjectInputStream aInputStream) throws ClassNotFoundException, java.io.IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new RoutingConnectionView(this));

    }

    private class RoutingConnectionView implements View<PObject> {
        public RoutingConnectionView(RoutingConnection routingConnection, RoutingSocket<?> start, RoutingSocket<?> end) {
        }

        public RoutingConnectionView(RoutingConnection routingConnection) {
        }

        @Override
        public PObject getModel() {
            return null;
        }

        @Override
        public void display(PGraphics graphics) {
            //line from start to end
            float[] start = _inlet.getPosition();
            float[] end = _outlet.getPosition();
            graphics.pushStyle();
            graphics.stroke(Config.THEME_COLOR_ROUTING_CONNECTION);
            graphics.strokeWeight(2);
            graphics.line(start[0], start[1], end[0], end[1]);
            graphics.popStyle();

        }

        @Override
        public boolean isMouseOver(int mouseX, int mouseY) {
            return false;
        }
    }
}