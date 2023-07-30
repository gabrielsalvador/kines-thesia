package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.routing.Inlet;
import me.gabrielsalvador.pobject.routing.Outlet;
import me.gabrielsalvador.pobject.routing.RoutingSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.ArrayList;

public class PKeyboard extends PObject implements Outlet {


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
    public ArrayList<Inlet> getInlets() {
        return null;
    }

    @Override
    public ArrayList<Outlet> getOutlets() {
        return null;
    }


    @Override
    public void receive(String message) {

    }
}
