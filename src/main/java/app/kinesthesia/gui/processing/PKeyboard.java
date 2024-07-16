package app.kinesthesia.gui.processing;


import app.kinesthesia.core.BodyComponent;
import app.kinesthesia.core.HologramBody;
import app.kinesthesia.core.PObject;
import app.kinesthesia.core.RoutingComponent;
import app.kinesthesia.gui.processing.routing.Inlet;


import java.io.Serial;


public class PKeyboard extends PObject implements Inlet {


    public PKeyboard() {
        super();
        initialize();

    }


    public void initialize() {
        super.initialize();





        HologramBody body = new HologramBody(this);
        addComponent(BodyComponent.class, body);







    }

    @Override
    public void receive(Object message) {

        //send pulse to routing
        RoutingComponent rc = getRoutingComponent();
        if(rc == null) return;
        try {
            rc.sendPulse(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize();
    }
}