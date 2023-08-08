package me.gabrielsalvador.pobject;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.routing.Inlet;
import me.gabrielsalvador.pobject.routing.Outlet;
import me.gabrielsalvador.pobject.routing.Routing;
import me.gabrielsalvador.pobject.routing.PSocket;
import me.gabrielsalvador.pobject.views.PEmitterView;
import org.jbox2d.common.Vec2;

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

    private ArrayList<PSocket<Inlet>> _inlets = new ArrayList<PSocket<Inlet>>();

    public PEmitter() {
        super();
        setView(new PEmitterView(this));

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

        setView(new PEmitterView(this));

    }

    @Override
    public ArrayList<PSocket<Inlet>> getInlets() {
        return _inlets;
    }

    @Override
    public ArrayList<PSocket<Outlet>> getOutlets() {
        return null;
    }

    @Override
    public void setInlets(ArrayList<PSocket<Inlet>> inlets) {
        _inlets = inlets;
    }

    @Override
    public void setOutlets(ArrayList<PSocket<Outlet>> outlets) {}

    @Override
    public void addInlet(PSocket<Inlet> inlet) {
        _inlets.add(inlet);
    }

    @Override
    public void addOutlet(PSocket<Outlet> outlet) {
        System.out.println("PKeyboard does not have inlets");
    }


    @Override
    public void receive(String message) {

        System.out.println("emitter got a message!");

        AppController.getInstance().addPhysicsNote(new Vec2(getPosition()[0],getPosition()[1]));
    }
}
