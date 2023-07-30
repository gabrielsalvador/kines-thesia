package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PGraphics;

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


}

class RoutingSocketView implements View<PObject> {

    RoutingSocket _model;
    public RoutingSocketView(RoutingSocket model) {
        _model = model;

    }

    @Override
    public RoutingSocket getModel() {
        return _model;
    }

    @Override
    public void display(PGraphics graphics) {
        //draw a circle bellow the Owner, centered with the Owner's width

    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        return false;
    }
}
