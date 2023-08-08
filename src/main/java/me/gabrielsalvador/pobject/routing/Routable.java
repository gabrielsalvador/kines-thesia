package me.gabrielsalvador.pobject.routing;

import java.util.ArrayList;

public interface Routable {
    public ArrayList<PSocket<Inlet>> getInlets();
    public ArrayList<PSocket<Outlet>> getOutlets();
    public void setInlets(ArrayList<PSocket<Inlet>> inlets);
    public void setOutlets(ArrayList<PSocket<Outlet>> outlets);
    public  void addInlet(PSocket<Inlet> inlet);
    public void addOutlet(PSocket<Outlet> outlet);

}
