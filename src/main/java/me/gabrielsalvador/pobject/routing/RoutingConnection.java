package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.views.View;
import processing.core.PGraphics;

import java.io.Serial;

public class RoutingConnection extends PObject {

    private PSocket<Inlet> _source;
    private PSocket<Outlet> _destination;

    public RoutingConnection(PSocket<?> inlet, PSocket<?> outlet) {
        super();
        _source = (PSocket<Inlet>) inlet;
        _source.addRouting(this);
        _destination = (PSocket<Outlet>) outlet;
        _destination.addRouting(this);
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

    public PSocket<Inlet> getSource() {
        return _source;
    }

   public PSocket<Outlet> getDestination(){
        return _destination;
   }

    private class RoutingConnectionView implements View<PObject> {
        public RoutingConnectionView(RoutingConnection routingConnection, PSocket<?> start, PSocket<?> end) {
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
            float[] start = _source.getPosition();
            float[] end = _destination.getPosition();
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
