package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.views.RoutingConnectionView;
import me.gabrielsalvador.pobject.views.View;
import processing.core.PApplet;
import processing.core.PGraphics;
import java.io.Serial;

public class RoutingConnection extends PObject {

    private final PSocket _source;
    private final PSocket _destination;

    public RoutingConnection(PSocket inlet, PSocket outlet) {
        super();
        _source = inlet;
        _source.addRouting(this);
        _destination = outlet;
        _destination.addRouting(this);
        addComponent(RoutingComponent.class, new RoutingComponent(this,RoutingComponent.RouterType.CONNECTION));
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

    public PSocket getSource() {
        return _source;
    }

   public PSocket getDestination(){
        return _destination;
   }


}
