package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObject;

import java.util.ArrayList;

public interface Routable {
    ArrayList<PSocket> getInlets();
    ArrayList<PSocket> getOutlets();
    void setInlets(ArrayList<PSocket> inlets);
    void setOutlets(ArrayList<PSocket> outlets);
    void addInlet(PSocket inlet);
    void addOutlet(PSocket outlet);

    default void initializeRouting() {
        // go through the routing annotations and add the inlets and outlets

        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Routing routing = currentClass.getAnnotation(Routing.class);
            if (routing != null) {
                for (SetInlet inlet : routing.inlets()) {
                    if (this instanceof Inlet) {
                        ArrayList<PSocket> inlets = this.getInlets();
                        if (inlets == null) {
                            this.setInlets(new ArrayList<PSocket>());
                        }
                        PSocket i = new PSocket((PObject) this);
                        AppController.getInstance().addRoutingSocket((PObject) this,i);
                        this.addInlet(i);
                    }
                }
                for (SetOutlet outlet : routing.outlets()) {
                    if (this instanceof Outlet) {
                        ArrayList<PSocket> outlets = this.getOutlets();
                        if (outlets == null) {
                            this.setOutlets(new ArrayList<PSocket>());
                        }
                        PSocket o = new PSocket((PObject) this);
                        AppController.getInstance().addRoutingSocket((PObject) this,o);
                        this.addOutlet(o);
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }
    }
}
