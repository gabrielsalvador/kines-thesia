package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObject;

import java.util.ArrayList;

public interface Routable {
    ArrayList<PObject> getInlets();
    ArrayList<PObject> getOutlets();
    void setInlets(ArrayList<PObject> inlets);
    void setOutlets(ArrayList<PObject> outlets);
    void addInlet(PObject inlet);
    void addOutlet(PObject outlet);

    default void initializeRouting() {
        // go through the routing annotations and add the inlets and outlets

        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Routing routing = currentClass.getAnnotation(Routing.class);
            if (routing != null) {
                for (SetInlet inlet : routing.inlets()) {
                    if (this instanceof Inlet) {
                        ArrayList<PObject> inlets = this.getInlets();
                        if (inlets == null) {
                            this.setInlets(new ArrayList<PObject>());
                        }
//                        PObject i = new PObject((PObject) this);
//                        AppController.getInstance().addPObject((PObject) this,i);
//                        this.addInlet(i);
                    }
                }
                for (SetOutlet outlet : routing.outlets()) {
                    if (this instanceof Outlet) {
                        ArrayList<PObject> outlets = this.getOutlets();
                        if (outlets == null) {
                            this.setOutlets(new ArrayList<PObject>());
                        }
//                        PObject o = new PObject((PObject) this);
//                        AppController.getInstance().addRoutingSocket((PObject) this,o);
//                        this.addOutlet(o);
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }
    }
}
