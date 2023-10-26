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
import me.gabrielsalvador.utils.MathUtils;

import static me.gabrielsalvador.utils.MathUtils.isPointOnLine;

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

                // Apply the rotation
                graphics.rotate(body.getAngle());

                PhysicsManager pm = PhysicsManager.getInstance();
                for (int i = 0; i < polygon.m_vertices.length-1; i++) {

                    graphics.line(pm.coordWorldToPixels(polygon.m_vertices[i].x, polygon.m_vertices[i].y).x, pm.coordWorldToPixels(polygon.m_vertices[i].x, polygon.m_vertices[i].y).y, pm.coordWorldToPixels(polygon.m_vertices[i+1].x, polygon.m_vertices[i+1].y).x, pm.coordWorldToPixels(polygon.m_vertices[i+1].x, polygon.m_vertices[i+1].y).y);


                }
                graphics.line(pm.coordWorldToPixels(polygon.m_vertices[polygon.m_vertices.length-1].x, polygon.m_vertices[polygon.m_vertices.length-1].y).x, pm.coordWorldToPixels(polygon.m_vertices[polygon.m_vertices.length-1].x, polygon.m_vertices[polygon.m_vertices.length-1].y).y, pm.coordWorldToPixels(polygon.m_vertices[0].x, polygon.m_vertices[0].y).x, pm.coordWorldToPixels(polygon.m_vertices[0].x, polygon.m_vertices[0].y).y);
                graphics.popMatrix();



                break;
            default:
                break;
        }

    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        PhysicsManager pm = PhysicsManager.getInstance();
        Body body = model.getJBox2DBody();
        Shape shape = body.getFixtureList().getShape();
        Vec2 bodyPixelPos = pm.coordWorldToPixels(body.getPosition().x, body.getPosition().y).add(model.getPixelPosition());

        if (shape.getType() == ShapeType.CIRCLE) {
            float radius = shape.getRadius();
            return (mouseX >= bodyPixelPos.x - radius && mouseX <= bodyPixelPos.x + radius) &&
                    (mouseY >= bodyPixelPos.y - radius && mouseY <= bodyPixelPos.y + radius);
        }

        if (shape.getType() == ShapeType.POLYGON) {
            PolygonShape polygon = (PolygonShape) shape;
            boolean inside = false;
            float angle = body.getAngle();

            for (int i = 0, j = polygon.getVertexCount() - 1; i < polygon.getVertexCount(); j = i++) {
                Vec2 vertexI = MathUtils.rotatePoint(0, 0, angle, polygon.m_vertices[i]);
                Vec2 vertexJ = MathUtils.rotatePoint(0, 0, angle, polygon.m_vertices[j]);
                Vec2 viPixelSpace = pm.coordWorldToPixels(vertexI.add(body.getPosition()).x, vertexI.add(body.getPosition()).y);
                Vec2 vjPixelSpace = pm.coordWorldToPixels(vertexJ.add(body.getPosition()).x, vertexJ.add(body.getPosition()).y);

                if (((viPixelSpace.y > mouseY) != (vjPixelSpace.y > mouseY)) &&
                        (mouseX < (vjPixelSpace.x - viPixelSpace.x) * (mouseY - viPixelSpace.y) / (vjPixelSpace.y - viPixelSpace.y) + viPixelSpace.x)) {
                    inside = !inside;
                }
            }
            return inside;
        }
        return false;
    }





}
