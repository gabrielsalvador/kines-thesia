package me.gabrielsalvador.pobject;

import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
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

    int _periodicityIn16thNotes = 16;
    int _internalBeatCounter = 0;

    private transient BlinkingLigth _blinkingLigth;
    public PMetronome() {
        super();


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
        _internalBeatCounter = (_internalBeatCounter + 1) % _periodicityIn16thNotes;

        if( _internalBeatCounter == 0){

            RoutingComponent rc = getRoutingComponent();
            if(rc == null) return;
            _blinkingLigth.blink();
            getRoutingComponent().sendPulse();

        }

    }

    @Serial
    private void readObject(java.io.ObjectInputStream in)
            throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize();
    }
}
