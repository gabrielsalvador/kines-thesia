package me.gabrielsalvador.gui.routing;

public interface Inlet extends Routable{
    void receive(Object message);
}
