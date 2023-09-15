package me.gabrielsalvador.pobject.components.body.shape;

import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class JShape extends AbstractShape {

    private PhysicsBodyComponent _owner;
    private Shape _shape;

    public JShape(Shape shape, PhysicsBodyComponent owner) {
        _owner = owner;
        _shape = shape;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float x, float y) {
        float[] boundaries = getBoundaries();
        float[] pixels = new float[boundaries.length];
        //convert to pixels
        pixels[0] = boundaries[0] * PhysicsManager.getInstance().worldToPixelScale(boundaries[0]);
        pixels[1] = boundaries[1] * PhysicsManager.getInstance().worldToPixelScale(boundaries[1]);
        pixels[2] = boundaries[2] * PhysicsManager.getInstance().worldToPixelScale(boundaries[2]);
        pixels[3] = boundaries[3] * PhysicsManager.getInstance().worldToPixelScale(boundaries[3]);
        return mouseX > pixels[0] + x && mouseX < pixels[2] + x && mouseY > pixels[1] + y && mouseY < pixels[3] + y;
    }

    @Override
    public void display(PGraphics graphics, float x, float y) {


        graphics.pushMatrix();
        float angle = _owner.getAngle();
        graphics.translate(x , y );
        graphics.rotate(angle);

        if (_shape instanceof org.jbox2d.collision.shapes.CircleShape) {
            org.jbox2d.collision.shapes.CircleShape circle = (org.jbox2d.collision.shapes.CircleShape) _shape;
            float radius = circle.m_radius;
            graphics.ellipse(0, 0, 2 * radius, 2 * radius);

        } else if (_shape instanceof org.jbox2d.collision.shapes.PolygonShape) {
            org.jbox2d.collision.shapes.PolygonShape polygon = (org.jbox2d.collision.shapes.PolygonShape) _shape;
            int vertexCount = polygon.m_count;
            graphics.beginShape();
            for (int i = 0; i < vertexCount; i++) {
                Vec2 vertex = PhysicsManager.getInstance().coordWorldToPixels(polygon.m_vertices[i].x, polygon.m_vertices[i].y);
                graphics.vertex(vertex.x, vertex.y);
            }
            graphics.endShape(PGraphics.CLOSE);

        } else if (_shape instanceof org.jbox2d.collision.shapes.EdgeShape) {
            org.jbox2d.collision.shapes.EdgeShape edge = (org.jbox2d.collision.shapes.EdgeShape) _shape;
            Vec2 vertex1 = edge.m_vertex1;
            Vec2 vertex2 = edge.m_vertex2;
            graphics.line(vertex1.x, vertex1.y, vertex2.x, vertex2.y);

        } else if (_shape instanceof org.jbox2d.collision.shapes.ChainShape) {
            org.jbox2d.collision.shapes.ChainShape chain = (org.jbox2d.collision.shapes.ChainShape) _shape;
            int vertexCount = chain.m_count;
            for (int i = 0; i < vertexCount - 1; i++) {
                Vec2 vertexA = chain.m_vertices[i];
                Vec2 vertexB = chain.m_vertices[i + 1];
                graphics.line(vertexA.x, vertexA.y, vertexB.x, vertexB.y);
            }

        } else {
            System.out.println("Unknown shape type");
        }
        graphics.popMatrix();
    }

    @Override
    public float[] getBoundaries() {
        AABB aabb = new AABB();
        Transform transform = new Transform(); // assuming an identity transform
        _shape.computeAABB(aabb, transform, 0);
        Vec2 lowerBound = aabb.lowerBound;
        Vec2 upperBound = aabb.upperBound;
        return new float[] {lowerBound.x, lowerBound.y, upperBound.x, upperBound.y};
    }
}
