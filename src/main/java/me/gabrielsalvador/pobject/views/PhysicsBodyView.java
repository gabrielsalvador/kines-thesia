package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.BodyData;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.components.body.shape.PShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import processing.core.PGraphics;

public class PhysicsBodyView implements View<Component> {
    private final PhysicsBodyComponent model;

    public PhysicsBodyView(PhysicsBodyComponent physicsBodyComponent) {
        model = physicsBodyComponent;
    }

    @Override
    public Component getModel() {
        return null;
    }

    @Override
    public void display(PGraphics graphics, Component model) {
        PhysicsBodyComponent physicsBodyComponent = (PhysicsBodyComponent) model;
        Body body = physicsBodyComponent.getJBox2DBody();
        Shape shape = body.getFixtureList().getShape();


        switch (shape.getType()) {
            case CIRCLE:
                Vec2 position = body.getPosition();
                Vec2 pixelPosition = PhysicsManager.getInstance().coordWorldToPixels(position.x, position.y);
                float radius = shape.getRadius();
                graphics.ellipseMode(graphics.CENTER);
                graphics.ellipse(pixelPosition.x, pixelPosition.y, radius * 2, radius * 2);

                break;
            case POLYGON:

                PolygonShape polygon = (PolygonShape) body.getFixtureList().getShape();
                graphics.pushMatrix();
                graphics.translate(physicsBodyComponent.getPixelPosition().x, physicsBodyComponent.getPixelPosition().y);
                for (int i = 0; i < polygon.m_vertices.length-1; i++) {
                    graphics.line(polygon.m_vertices[i].x, polygon.m_vertices[i].y, polygon.m_vertices[i+1].x, polygon.m_vertices[i+1].y);
                }

                graphics.line(polygon.m_vertices[polygon.m_vertices.length-1].x, polygon.m_vertices[polygon.m_vertices.length-1].y, polygon.m_vertices[0].x, polygon.m_vertices[0].y);
                graphics.popMatrix();



                break;
            default:
                break;
        }

    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {

        Body body = model.getJBox2DBody();
        Shape shape = body.getFixtureList().getShape();
        if (shape.getType() == ShapeType.CIRCLE) {
            Vec2 position = body.getPosition();
            Vec2 pixelPosition = PhysicsManager.getInstance().coordWorldToPixels(position.x, position.y);
            float radius = shape.getRadius();
            return (mouseX >= pixelPosition.x - radius && mouseX <= pixelPosition.x + radius) &&
                    (mouseY >= pixelPosition.y - radius && mouseY <= pixelPosition.y + radius);
        }


        return false;
    }
}
