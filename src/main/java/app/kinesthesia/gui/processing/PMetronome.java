package app.kinesthesia.gui.processing;

import app.kinesthesia.core.HologramBody;
import app.kinesthesia.core.PObject;
import app.kinesthesia.core.RoutingComponent;
import app.kinesthesia.gui.processing.views.BlinkingLigth;
import app.kinesthesia.core.Device;
import app.kinesthesia.kinescript.ast.KFunction;

import java.io.Serial;

public class PMetronome extends PObject implements Device {



    int _periodicityIn16thNotes = 16;
    @InspectableProperty(displayName = "Periodicity")
    public int getPeriodicityIn16thNotes() {
        return _periodicityIn16thNotes;
    }
    @InspectableProperty.SetterFor("Periodicity")
    public void setPeriodicityIn16thNotes(int periodicityIn16thNotes) {
        _periodicityIn16thNotes = periodicityIn16thNotes;
    }
    public KFunction onPulse;
    int _internalBeatCounter = 0;

    private transient BlinkingLigth _blinkingLigth;
    public PMetronome() {
        super();

        initialize();
    }

    public void initialize() {
        super.initialize();
        HologramBody body = getBodyComponent();
    }


    @Override
    public void clockTick() {
        _internalBeatCounter = (_internalBeatCounter + 1) % _periodicityIn16thNotes;

        if( _internalBeatCounter == 0){

            RoutingComponent rc = getRoutingComponent();
            if(rc == null) return;
            _blinkingLigth.blink();
            if(onPulse != null) onPulse.execute();

        }

    }

    @Serial
    private void readObject(java.io.ObjectInputStream in)
            throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize();
    }

    public BlinkingLigth getBlinkingLigth() {
        return _blinkingLigth;
    }
}
