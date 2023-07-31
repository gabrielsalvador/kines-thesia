package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.routing.Inlet;
import me.gabrielsalvador.pobject.routing.Outlet;
import me.gabrielsalvador.pobject.routing.Routing;
import me.gabrielsalvador.pobject.routing.RoutingSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.ArrayList;

@Routing(
        inlets = {
                @me.gabrielsalvador.pobject.routing.SetInlet(name = "trigger", type = Integer.class)
        }
)
public class PEmitter extends PObject implements Inlet {

    private ArrayList<RoutingSocket<Inlet>> _inlets = new ArrayList<RoutingSocket<Inlet>>();

    public PEmitter() {
        super();
        setView(new PKeyboardView(this));

    }

    @Override
    public void onEnter(int x, int y) {
        System.out.println("pkeyboard on enter");
    }

    @Override
    public void onLeave(int x, int y) {

    }


    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PKeyboardView(this));

    }

    @Override
    public ArrayList<RoutingSocket<Inlet>> getInlets() {
        return _inlets;
    }

    @Override
    public ArrayList<RoutingSocket<Outlet>> getOutlets() {
        return null;
    }

    @Override
    public void setInlets(ArrayList<RoutingSocket<Inlet>> inlets) {
        _inlets = inlets;
    }

    @Override
    public void setOutlets(ArrayList<RoutingSocket<Outlet>> outlets) {}

    @Override
    public void addInlet(RoutingSocket<Inlet> inlet) {
        _inlets.add(inlet);
    }

    @Override
    public void addOutlet(RoutingSocket<Outlet> outlet) {
        System.out.println("PKeyboard does not have inlets");
    }


    @Override
    public void receive(String message) {

    }
}
