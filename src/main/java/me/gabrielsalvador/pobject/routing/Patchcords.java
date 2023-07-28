package me.gabrielsalvador.pobject.routing;

import java.io.Serializable;

public class Patchcords implements Serializable {
    private final Outlet outlet;
    private final Inlet inlet;

    public Patchcords(Outlet outlet, Inlet inlet) {
        this.outlet = outlet;
        this.inlet = inlet;
    }

    public void send(Object message) {
        this.inlet.receive(message);
    }
}
