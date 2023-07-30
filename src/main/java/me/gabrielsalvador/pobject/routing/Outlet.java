package me.gabrielsalvador.pobject.routing;

import java.util.ArrayList;

public interface Outlet extends Routable{
    public ArrayList<Inlet> getInlets();
    public ArrayList<Outlet> getOutlets();
}
