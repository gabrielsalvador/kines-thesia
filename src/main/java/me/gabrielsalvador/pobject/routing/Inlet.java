package me.gabrielsalvador.pobject.routing;

public interface Inlet extends Routable{
    void receive(Object message);
}
