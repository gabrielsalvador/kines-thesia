package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.routing.Outlet;
import me.gabrielsalvador.pobject.routing.PatchSocket;
import me.gabrielsalvador.pobject.routing.Patchcord;
import me.gabrielsalvador.pobject.routing.Trigger;
import me.gabrielsalvador.pobject.routing.annotations.InletAnnotation;
import me.gabrielsalvador.pobject.routing.annotations.InletsAnnotation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.HashSet;


@InletsAnnotation({
        @InletAnnotation(name = "trigger", type = Trigger.class),
})
public class PKeyboard extends PObject implements Outlet<Trigger> {

    HashSet<Patchcord> patchcords = new HashSet<>();
    private PatchSocket _patchSocket;

    public PKeyboard() {
        super();
        setView(new PKeyboardView(this));

    }


    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PKeyboardView(this));

    }

    @Override
    public void send(Trigger message) {
        patchcords.forEach(patchcord -> patchcord.send(message));
    }

    @Override
    public void setPatchSocket(PatchSocket endSocket) {
        this._patchSocket = endSocket;
    }

    @Override
    public PatchSocket getPatchSocket() {
        return this._patchSocket;

    }
}
