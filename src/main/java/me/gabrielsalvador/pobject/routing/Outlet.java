package me.gabrielsalvador.pobject.routing;

public interface Outlet<T> {
    void send(T message);

}
