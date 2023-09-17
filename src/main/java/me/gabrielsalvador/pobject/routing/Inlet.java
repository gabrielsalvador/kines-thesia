package me.gabrielsalvador.pobject.routing;

public interface Inlet extends Routable{
    void receive(String message);
}
