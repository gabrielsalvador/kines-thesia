package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PGraphics;

import java.io.Serial;

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

}

class RoutingSocketView implements View<PObject> {

    RoutingSocket<?> _model;
    public RoutingSocketView(RoutingSocket<?> model) {
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
        float[] size = _model.getOwner().getSize();
        graphics.ellipse(position[0], position[1] + size[1] / 2, 10, 10);

    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        return false;
    }
}
