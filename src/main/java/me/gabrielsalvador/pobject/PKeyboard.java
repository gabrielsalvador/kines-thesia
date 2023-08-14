package me.gabrielsalvador.pobject;

import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.pobject.components.HologramBody;
import me.gabrielsalvador.pobject.routing.*;
import me.gabrielsalvador.pobject.views.PKeyboardView;
import me.gabrielsalvador.pobject.views.RoutingSocketView;
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

    private ArrayList<PSocket<Outlet>> _outlets;

    public PKeyboard() {
        super();
        initialize();
        initializeRouting();
    }

    protected void initialize() {
        addComponent(BodyComponent.class,new HologramBody());
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
    public ArrayList<PSocket<Inlet>> getInlets() {
        return null;
    }

    @Override
    public ArrayList<PSocket<Outlet>> getOutlets() {
        return _outlets;
    }

    @Override
    public void setInlets(ArrayList<PSocket<Inlet>> inlets) {}

    @Override
    public void setOutlets(ArrayList<PSocket<Outlet>> outlets) {
        _outlets = outlets;
    }

    @Override
    public void addInlet(PSocket<Inlet> inlet) {
        System.out.println("PKeyboard does not have inlets");
    }

    @Override
    public void addOutlet(PSocket<Outlet> outlet) {
        _outlets.add(outlet);
    }


    @Override
    public void send(String message) {
        for (PSocket<?> outlet : getOutlets() ){
            for(RoutingConnection r : outlet.getRoutings()){
                if(r.getSource().getOwner() == this){
                ((Inlet)r.getDestination().getOwner()).receive(message);
                }
            }
            ( (RoutingSocketView) outlet.getView() ).getBlinkingLigth().blink();
        }
    }

    @Override
    public void receive(String message) {
        send(message);
    }
}
