package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.BlinkingLigth;
import me.gabrielsalvador.views.View;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class RoutingSocketView<T extends Routable> implements View<PObject> {

    static protected int SIZE_X = 7;
    RoutingSocket<T> _model;
    private BlinkingLigth _blinkingLigth = new BlinkingLigth(SIZE_X);

    public RoutingSocketView(RoutingSocket<T> model) {
        _model = model;
    }

    @Override
    public RoutingSocket<?> getModel() {
        return _model;
    }

    @Override
    public void display(PGraphics graphics) {
        //draw a circle bellow the Owner, centered with the Owner's width
        float[] position = _model.getOwner().getPosition();
        float[] ownerSize = _model.getOwner().getSize();
        graphics.pushStyle();
        graphics.pushMatrix();
        graphics.translate(position[0], position[1] + ownerSize[1] + SIZE_X);
        graphics.ellipseMode(PConstants.CENTER);
        graphics.fill(_model.getIsHovered() ? 127 : 255);
        graphics.ellipse(0,0,SIZE_X+4,SIZE_X+4);
        _blinkingLigth.display(graphics);
        graphics.popMatrix();

    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        float[] position = _model.getOwner().getPosition();
        float[] ownerSize = _model.getOwner().getSize();

        // calculate the center of the ellipse
        float centerX = position[0];
        float centerY = position[1] + ownerSize[1] + SIZE_X;

        // calculate the distance between the mouse and the center of the ellipse
        float distance = PApplet.dist(mouseX, mouseY, centerX, centerY);

        // check if the distance is less than the radius (SIZE_X / 2)
        return distance <= SIZE_X / 2 + 4; //+4 pixels for a better hover
    }

    public BlinkingLigth getBlinkingLigth() {
        return _blinkingLigth;
    }
}
