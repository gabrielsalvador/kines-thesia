package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PRubberbandPreset;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class PRubberbandView implements View<Component> {

    PhysicsBodyComponent _owner;


    public PRubberbandView(BodyComponent owner) {
        _owner = (PhysicsBodyComponent) owner;
    }

    @Override
    public Component getModel() {
        return _owner;
    }

    @Override
    public void display(PGraphics graphics, Component model) {

        Shape shape = _owner.getJBox2DBody().getFixtureList().getShape();
        PolygonShape polygon = (PolygonShape) shape;

        //draw a line between the two points
        Vec2 vertex1 = polygon.m_vertices[0];
        Vec2 vertex2 = polygon.m_vertices[1];
        Vec2 pixelVertex1 = PhysicsManager.getInstance().coordPixelsToWorld(vertex1.x, vertex1.y);
        Vec2 pixelVertex2 = PhysicsManager.getInstance().coordPixelsToWorld(vertex2.x, vertex2.y);

        graphics.stroke(255);
        graphics.strokeWeight(2);
        graphics.line(pixelVertex1.x, pixelVertex1.y, pixelVertex2.x, pixelVertex2.y);







    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        return false;
    }
}
