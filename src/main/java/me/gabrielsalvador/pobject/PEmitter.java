package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.routing.*;
import me.gabrielsalvador.pobject.routing.annotations.InletAnnotation;
import me.gabrielsalvador.pobject.routing.annotations.InletsAnnotation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.HashSet;


@InletsAnnotation({
        @InletAnnotation(name = "trigger", type = Trigger.class),
})
public class PEmitter extends PObject implements Inlet<Trigger> {

    HashSet<Patchcord> patchcords = new HashSet<>();
    private PatchSocket _patchSocket;

    public PEmitter() {
        super();
        setView(new PEmitterView(this));

    }


    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PEmitterView(this));

    }



    @Override
    public void receive(Trigger message) {

    }

    public void setPatchSocket(PatchSocket patchSocket) {
        this._patchSocket = patchSocket;
    }
    @Override
    public PatchSocket getPatchSocket() {
        return this._patchSocket;
    }
}
