package me.gabrielsalvador.pobject;


import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.components.body.HologramBodyView;
import me.gabrielsalvador.pobject.routing.*;
import me.gabrielsalvador.pobject.views.PKeyboardView;

import java.io.Serial;


public class PKeyboard extends PObject implements  Inlet{


    public PKeyboard() {
        super();
        initialize();

    }


    public void initialize() {
        super.initialize();
        AppController.getInstance().getSequencerController().registerPObject(this);




        HologramBody body = new HologramBody(this);
        PKeyboardView view = new PKeyboardView(body);
        addComponent(BodyComponent.class, body);
        body.setView(view);






    }

    @Override
    public void receive(Object message) {
        //blink light
        HologramBodyView view = (HologramBodyView) getBodyComponent().getView();
        ((PKeyboardView ) view).getBlinkingLigth().blink();

        //send pulse to routing
        RoutingComponent rc = getRoutingComponent();
        if(rc == null) return;
        rc.sendPulse(message);

    }


    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize();
    }
}