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
        outlets = {
                @me.gabrielsalvador.pobject.routing.SetOutlet(name = "outlet", type = String.class)
        }
)
public class PKeyboard extends PObject implements Outlet {

    private ArrayList<RoutingSocket<Outlet>> _outlets = new ArrayList<RoutingSocket<Outlet>>();

    public PKeyboard() {
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
        return null;
    }

    @Override
    public ArrayList<RoutingSocket<Outlet>> getOutlets() {
        return _outlets;
    }

    @Override
    public void setInlets(ArrayList<RoutingSocket<Inlet>> inlets) {

    }

    @Override
    public void setOutlets(ArrayList<RoutingSocket<Outlet>> outlets) {
        _outlets = outlets;
    }

    @Override
    public void addInlet(RoutingSocket<Inlet> inlet) {
        System.out.println("PKeyboard does not have inlets");
    }

    @Override
    public void addOutlet(RoutingSocket<Outlet> outlet) {
        _outlets.add(outlet);
    }


    @Override
    public void receive(String message) {

    }
}
