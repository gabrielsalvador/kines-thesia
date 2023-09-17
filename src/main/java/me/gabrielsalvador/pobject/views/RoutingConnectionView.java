package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.routing.PSocket;
import me.gabrielsalvador.pobject.routing.RoutingConnection;
import processing.core.PApplet;
import processing.core.PGraphics;

public class RoutingConnectionView implements View<PObject> {

    private RoutingConnection _model;
    private RoutingComponent _routingComponent;
    private  PSocket _source;
    private  PSocket _destination;

    public RoutingConnectionView(RoutingConnection routingConnection){
        _model = routingConnection;
        _routingComponent = _model.getComponent(RoutingComponent.class);
        _source = _model.getSource();
        _destination = _model.getDestination();
    }



    @Override
    public PObject getModel() {
        return null;
    }

    @Override
    public void display(PGraphics graphics) {
        int divisions = (int)_routingComponent.getProperty("Subdivisions").getValue();
        divisions = PApplet.max(divisions, 1);

        float[] start = _source.getPixelPosition();
        float[] end = _destination.getPixelPosition();

        // Calculate the delta for each division
        float deltaX = (end[0] - start[0]) / divisions;
        float deltaY = (end[1] - start[1]) / divisions;

        graphics.pushStyle();

        if (_model.getIsSelected()) {
            graphics.stroke(Config.THEME_COLOR_SELECTED);
        } else {
            graphics.stroke(Config.THEME_COLOR_ROUTING_CONNECTION);
        }

        graphics.strokeWeight(2);

        // Render each segment of the ruler
        for (int i = 0; i < divisions; i++) {
            float x1 = start[0] + (deltaX * i);
            float y1 = start[1] + (deltaY * i);
            float x2 = start[0] + (deltaX * (i + 1));
            float y2 = start[1] + (deltaY * (i + 1));

            graphics.line(x1, y1, x2, y2);
            graphics.ellipse(x1, y1, 5, 5);
        }

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