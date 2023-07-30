package me.gabrielsalvador.pobject.routing;

import java.util.ArrayList;

public interface Routable {
    public ArrayList<RoutingSocket<Inlet>> getInlets();
    public ArrayList<RoutingSocket<Outlet>> getOutlets();
    public void setInlets(ArrayList<RoutingSocket<Inlet>> inlets);
    public void setOutlets(ArrayList<RoutingSocket<Outlet>> outlets);

    public  void addInlet(RoutingSocket<Inlet> inlet);
    public void addOutlet(RoutingSocket<Outlet> outlet);

}
