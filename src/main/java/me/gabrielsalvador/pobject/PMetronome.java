package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.views.BlinkingLigth;
import me.gabrielsalvador.pobject.views.PMetronomeView;
import me.gabrielsalvador.pobject.views.View;
import me.gabrielsalvador.sequencing.Clock;
import me.gabrielsalvador.sequencing.Device;

import java.io.Serial;

public class PMetronome extends PObject implements Device {

    private BlinkingLigth _blinkingLigth;
    public PMetronome() {
        super();
        Clock.getInstance().addDevice(this);
        initialize();
    }

    public void initialize() {
        super.initialize();
        HologramBody body = getBodyComponent();
        PMetronomeView view = new PMetronomeView(body);
        body.setView(view);
        _blinkingLigth = view.getBlinkingLight();
    }


    @Override
    public void clockTick() {

        RoutingComponent rc = getRoutingComponent();
        if(rc == null) return;
        _blinkingLigth.blink();
        getRoutingComponent().sendPulse();

    }

    @Serial
    public void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize();
    }
}
