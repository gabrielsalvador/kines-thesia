package me.gabrielsalvador.pobject;

import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.routing.Inlet;
import me.gabrielsalvador.pobject.routing.Outlet;
import me.gabrielsalvador.pobject.routing.Routing;
import me.gabrielsalvador.pobject.routing.RoutingSocket;
import me.gabrielsalvador.sequencing.Clock;
import me.gabrielsalvador.sequencing.SequencerController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.ArrayList;

import static me.gabrielsalvador.Config.MAIN_SEQUENCER;

@Routing(
        outlets = {
                @me.gabrielsalvador.pobject.routing.SetOutlet(name = "outlet", type = String.class)
        }
)
public class PKeyboard extends PObject implements Outlet,Inlet {

    private ArrayList<RoutingSocket<Outlet>> _outlets = new ArrayList<RoutingSocket<Outlet>>();

    public PKeyboard() {
        super();
        initialize();

    }

    private void initialize() {
        setView(new PKeyboardView(this));
        SequencerController sequencer = (SequencerController) Sinesthesia.getInstance().getCP5().get(MAIN_SEQUENCER);
        sequencer.registerPObject(this);
    }

    @Override
    public void onEnter(int x, int y) {

    }

    @Override
    public void onLeave(int x, int y) {

    }


    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        initialize();

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
    public void setInlets(ArrayList<RoutingSocket<Inlet>> inlets) {}

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
    public void send(String message) {

    }

    @Override
    public void receive(String message) {
        System.out.println("pkeyboard got a message!");
    }
}
