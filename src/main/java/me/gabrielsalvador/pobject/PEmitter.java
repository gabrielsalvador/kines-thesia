package me.gabrielsalvador.pobject;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.BodyData;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.routing.Inlet;
import me.gabrielsalvador.pobject.routing.Outlet;
import me.gabrielsalvador.pobject.routing.Routing;
import me.gabrielsalvador.pobject.routing.PSocket;
import me.gabrielsalvador.pobject.views.PEmitterView;
import me.gabrielsalvador.sequencing.SequencerController;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.ArrayList;

import static me.gabrielsalvador.Config.MAIN_SEQUENCER;

@Routing(
        inlets = {
                @me.gabrielsalvador.pobject.routing.SetInlet(name = "trigger", type = Integer.class)
        }
)
public class PEmitter extends PObject implements Inlet {

    private ArrayList<PSocket<Inlet>> _inlets;

    public PEmitter() {
        super();
        initialize();
        initializeRouting();
    }

    @Override
    protected void initialize() {
        setView(new PEmitterView(this));
    }

    @Override
    public void onEnter(int x, int y) {}

    @Override
    public void onLeave(int x, int y) {}

    @Override
    public void remove(){
        SequencerController sequencer = (SequencerController) Sinesthesia.getInstance().getCP5().get(MAIN_SEQUENCER);
        sequencer.unregisterPObject(this);
    }

    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();
        initialize();
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

        AppController.getInstance().queueModification(() -> {
            PObject pObject = new PlayableNote();
            Vec2 emitterPos = getBodyComponent().getPixelPosition();
            BodyData bodyData = new BodyData();
            bodyData.x = emitterPos.x;
            bodyData.y = emitterPos.y;
            bodyData.shapeType = ShapeType.CIRCLE;
            PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(pObject,bodyData);
            bodyComponent.setType(BodyType.DYNAMIC);
            pObject.addComponent(BodyComponent.class, bodyComponent);
            bodyComponent.setPosition(getBodyComponent().getPosition());
            AppController.getInstance().addPObject(pObject);
        });


    }
}
