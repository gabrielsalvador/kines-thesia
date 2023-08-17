package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObject;

import java.util.ArrayList;

public interface Routable {
    public ArrayList<PSocket<Inlet>> getInlets();
    public ArrayList<PSocket<Outlet>> getOutlets();
    public void setInlets(ArrayList<PSocket<Inlet>> inlets);
    public void setOutlets(ArrayList<PSocket<Outlet>> outlets);
    public  void addInlet(PSocket<Inlet> inlet);
    public void addOutlet(PSocket<Outlet> outlet);

    public default void initializeRouting() {
        // go through the routing annotations and add the inlets and outlets

        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Routing routing = currentClass.getAnnotation(Routing.class);
            if (routing != null) {
                for (SetInlet inlet : routing.inlets()) {
                    if (this instanceof Inlet) {
                        ArrayList<PSocket<Inlet>> inlets = ((Inlet) this).getInlets();
                        if (inlets == null) {
                            ((Inlet)this).setInlets(new ArrayList<PSocket<Inlet>>());
                        }
                        PSocket<Inlet> i = new PSocket<Inlet>((PObject) this, Inlet.class);
                        AppController.getInstance().addRoutingSocket((PObject) this,i);
                        ((Inlet) this).addInlet(i);
                    }
                }
                for (SetOutlet outlet : routing.outlets()) {
                    if (this instanceof Outlet) {
                        ArrayList<PSocket<Outlet>> outlets = ((Outlet) this).getOutlets();
                        if (outlets == null) {
                            ( (Outlet) this).setOutlets(new ArrayList<PSocket<Outlet>>());
                        }
                        PSocket<Outlet> o = new PSocket<Outlet>((PObject) this,Outlet.class);
                        AppController.getInstance().addRoutingSocket((PObject) this,o);
                        ((Outlet) this).addOutlet(o);
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }
    }
}
