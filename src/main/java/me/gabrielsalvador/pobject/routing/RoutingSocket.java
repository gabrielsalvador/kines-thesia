package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.io.Serial;
import java.util.ArrayList;

public class RoutingSocket<T extends Routable> extends PObject{
    private PObject _owner;
    private Class<T> type;
    private String name;
    private RoutingSocketView view;
    public RoutingSocket(PObject _owner) {
        super();
        this._owner = _owner;
        setView(new RoutingSocketView(this));
    }

    public void setOwner(PObject owner) {
        _owner = owner;
    }
    public PObject getOwner() {
        return _owner;
    }

    @Serial
    private void readObject(java.io.ObjectInputStream aInputStream) throws ClassNotFoundException, java.io.IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new RoutingSocketView(this));

    }


    @Override
    public void onEnter(int x, int y) {

    }

    @Override
    public void onLeave(int x, int y) {

    }
}

class RoutingSocketView<T extends Routable> implements View<PObject> {

    private int SIZE_X = 7;
    RoutingSocket<T> _model;
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
        graphics.ellipseMode(PConstants.CENTER);
        graphics.fill(_model.getIsHovered() ? 127:255);
        graphics.ellipse(position[0], position[1] + ownerSize[1] + SIZE_X , SIZE_X, SIZE_X);

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
        return distance <= SIZE_X / 2;
    }
}
