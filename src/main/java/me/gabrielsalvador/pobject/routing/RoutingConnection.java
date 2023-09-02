package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.views.View;
import processing.core.PApplet;
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
        initialize();
    }

    @Override
    protected void initialize() {
        setView(new RoutingConnectionView(this));
    }

    @Override
    public void onEnter(int x, int y) {

    }

    @Override
    public void onLeave(int x, int y) {

    }

    @Override
    public void remove() {

    }

    @Serial
    private void readObject(java.io.ObjectInputStream aInputStream) throws ClassNotFoundException, java.io.IOException {
        // default deserialization
        aInputStream.defaultReadObject();
        initialize();
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
            float[] start = _source.getPixelPosition();
            float[] end = _destination.getPixelPosition();
            graphics.pushStyle();
            if(getIsSelected())
                graphics.stroke(Config.THEME_COLOR_SELECTED);
            else
                graphics.stroke(Config.THEME_COLOR_ROUTING_CONNECTION);
            graphics.strokeWeight(2);
            graphics.line(start[0], start[1], end[0], end[1]);
            graphics.popStyle();

        }

        @Override
        public boolean isMouseOver(int mouseX, int mouseY) {
            float[] start = _source.getPixelPosition();
            float[] end = _destination.getPixelPosition();

            // Find the distances from the point to the start and end of the line
            float distanceToStart = PApplet.dist(mouseX, mouseY, start[0], start[1]);
            float distanceToEnd = PApplet.dist(mouseX, mouseY, end[0], end[1]);

            // Length of the line
            float lineLength = PApplet.dist(start[0], start[1], end[0], end[1]);

            if (distanceToStart + distanceToEnd > lineLength + 0.5) {
                // The mouse is not over the extended line
                return false;
            }

            // Calculate the distance from the point to the line segment
            float A = mouseY - start[1] - (end[1] - start[1]) / (end[0] - start[0]) * (mouseX - start[0]);
            float lineDist = Math.abs(A) / (float) Math.sqrt(1 + Math.pow((end[1] - start[1]) / (end[0] - start[0]), 2));

            // Threshold for deciding whether the mouse is over the line
            float threshold = 2.0f;

            return lineDist <= threshold;
        }
    }
}
