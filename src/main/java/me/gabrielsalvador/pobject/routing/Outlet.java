package me.gabrielsalvador.pobject.routing;

import java.util.ArrayList;

public interface Outlet extends Routable{
    public void receive(String message);
}
