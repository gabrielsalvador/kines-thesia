package me.gabrielsalvador.pobject;

import me.gabrielsalvador.PGroup;
import me.gabrielsalvador.tools.gizmo.Gizmo;
import org.jbox2d.common.Vec2;

public class RoutingGizmo extends Gizmo {
    PGroup group;
    public RoutingGizmo(PGroup _group) {
        super();
        group = _group;
    }

    @Override
    public void onPressed() {

    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }

    @Override
    public Vec2[] getPositions() {
        PObject object = (PObject) group.get(0);
        if (object != null){
            Vec2 p = object.getBodyComponent().getPixelCenter();
            return new Vec2[]{p};
        }
        return null;
    }

    @Override
    public void onDragStart(PObject pObject, int[] mousePosition) {

    }
}
