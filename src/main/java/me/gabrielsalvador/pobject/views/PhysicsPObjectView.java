package me.gabrielsalvador.pobject.views;


import me.gabrielsalvador.pobject.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.PhysicsPObject;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import processing.core.PConstants;
import processing.core.PGraphics;

public class PhysicsPObjectView implements View<PhysicsPObject>{

    private PhysicsPObject _model;
    private PhysicsBodyComponent _bodyComponent;
    private Body _body;


    public PhysicsPObjectView(PhysicsPObject model) {
        _model = model;
        _bodyComponent = _model.getBodyComponent();
        _body = _bodyComponent.getJBox2DBody();
    }
    @Override
    public PhysicsPObject getModel() {
        return _model;
    }

    @Override
    public void display(PGraphics graphics) {

        org.jbox2d.common.Vec2 position = _body.getPosition();
        graphics.pushMatrix();
        graphics.translate(position.x, position.y);
        graphics.rotate(_body.getAngle());
        graphics.fill(255);  // white

        for (Fixture f = _body.getFixtureList(); f != null; f = f.getNext()) {
            if (f.getShape() instanceof org.jbox2d.collision.shapes.CircleShape) {
                org.jbox2d.collision.shapes.CircleShape circle = (org.jbox2d.collision.shapes.CircleShape) f.getShape();
                float radius = circle.m_radius;
                graphics.ellipse(0, 0, radius * 2, radius * 2);
            } else if (f.getShape() instanceof org.jbox2d.collision.shapes.PolygonShape) {
                org.jbox2d.collision.shapes.PolygonShape polygon = (org.jbox2d.collision.shapes.PolygonShape) f.getShape();
                int vertexCount = polygon.getVertexCount();
                graphics.beginShape();
                for (int i = 0; i < vertexCount; i++) {
                    org.jbox2d.common.Vec2 vertex = polygon.getVertex(i);
                    graphics.vertex(vertex.x, vertex.y);
                }
                graphics.endShape(PConstants.CLOSE);
            } else if (f.getShape() instanceof org.jbox2d.collision.shapes.EdgeShape) {
                org.jbox2d.collision.shapes.EdgeShape edge = (org.jbox2d.collision.shapes.EdgeShape) f.getShape();
                org.jbox2d.common.Vec2 vertex1 = edge.m_vertex1;
                org.jbox2d.common.Vec2 vertex2 = edge.m_vertex2;
                graphics.line(vertex1.x, vertex1.y, vertex2.x, vertex2.y);
            } else if (f.getShape() instanceof org.jbox2d.collision.shapes.ChainShape) {
                org.jbox2d.collision.shapes.ChainShape chain = (org.jbox2d.collision.shapes.ChainShape) f.getShape();
                int vertexCount = chain.m_count;
                graphics.beginShape();
                for (int i = 0; i < vertexCount - 1; i++) {
                    org.jbox2d.common.Vec2 vertex1 = chain.m_vertices[i];
                    org.jbox2d.common.Vec2 vertex2 = chain.m_vertices[i + 1];
                    graphics.line(vertex1.x, vertex1.y, vertex2.x, vertex2.y);
                }
                graphics.endShape();
            }
        }

        graphics.popMatrix();
    }


    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        org.jbox2d.common.Vec2 position = _model.getBodyComponent().getPosition();
        Vec2 mouse = new Vec2(mouseX,mouseY);
        float distance = org.jbox2d.common.MathUtils.distanceSquared(position, mouse);

        // Assuming circle shape for simplicity
        float radius = ((org.jbox2d.collision.shapes.CircleShape) _body.getFixtureList().getShape()).m_radius;

        return distance <= radius * radius;
    }
}

