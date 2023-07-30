package me.gabrielsalvador.pobject.routing;

public interface Inlet extends Routable{
    public void receive(String message);
}
