package me.gabrielsalvador.pobject.routing;

import java.util.ArrayList;

public interface Routable {

    ArrayList<RoutingConnection> _routings = new ArrayList<RoutingConnection>();
    public ArrayList<PSocket<Inlet>> getInlets();
    public ArrayList<PSocket<Outlet>> getOutlets();
    public void setInlets(ArrayList<PSocket<Inlet>> inlets);
    public void setOutlets(ArrayList<PSocket<Outlet>> outlets);
    public  void addInlet(PSocket<Inlet> inlet);
    public void addOutlet(PSocket<Outlet> outlet);
    public default  void addRouting(RoutingConnection r){
        _routings.add(r);
    }

    public default ArrayList<RoutingConnection> getRoutings(){
        return _routings;
    }

}
