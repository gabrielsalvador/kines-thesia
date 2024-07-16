package me.gabrielsalvador.gui.processing.tools.gizmo;


import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.PGroup;
import me.gabrielsalvador.gui.processing.tools.RoutingTool;
import me.gabrielsalvador.gui.processing.tools.ToolManager;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class RoutingGizmo extends Gizmo {

    public RoutingGizmo(PGroup _group) {
        super(_group);

    }

    @Override
    public void draw(PGraphics graphics) {
        graphics.pushStyle();
        graphics.stroke(255);
        // Define the start and end points of the arrow
        Vec2 start = getPositions()[0];
        Vec2 end = start.add(new Vec2(5, -5));

        graphics.ellipseMode(graphics.CENTER);
        graphics.ellipse(start.x, start.y, 10, 10);

        // Draw the shaft of the arrow
        graphics.line(start.x, start.y, end.x, end.y);

        // Calculate the direction of the arrow
        Vec2 dir = end.sub(start);
        dir.normalize();



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
            Vec2[] positions = new Vec2[1];
            positions[0] = object.getBodyComponent().getPosition();
            return positions;
        }
        return null;
    }

    @Override
    public void onDragStart(PObject pObject, int[] mousePosition) {

    }



}
