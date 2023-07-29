package me.gabrielsalvador.pobject.routing;

import java.io.Serializable;

public class Patchcord<T> implements Serializable {
    private final Outlet<T> outlet;
    private final Inlet<T> inlet;

    public Patchcord(Outlet outlet, Inlet inlet) {
        this.outlet = outlet;
        this.inlet = inlet;
    }

    public void send(T message) {
        this.inlet.receive(message);
    }
}
