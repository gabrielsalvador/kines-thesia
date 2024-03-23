package me.gabrielsalvador.tools.gizmo;

import me.gabrielsalvador.PGroup;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.tools.RoutingTool;
import me.gabrielsalvador.tools.ToolManager;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class RoutingGizmo extends Gizmo {

    public RoutingGizmo(PGroup _group) {
        super(_group);

    }

    @Override
    public void draw(PGraphics graphics) {
        graphics.pushStyle();
        graphics.fill(255, 0, 0);
        Vec2[] _position = getPositions();
        graphics.ellipseMode(3);
        graphics.fill(255, 255, 255);
        graphics.stroke(0);
        for (Vec2 position : _position) {
            graphics.ellipse(position.x, position.y, 10, 10);
        }
        graphics.popStyle();
    }


    @Override
    public void onPressed() {
        RoutingTool tool = (RoutingTool) ToolManager.getInstance().selectTool(RoutingTool.class);
        tool.onPressed(selectedObjects.get(0),null);


    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }

    @Override
    public Vec2[] getPositions() {
        PObject object = (PObject) selectedObjects.get(0);
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
