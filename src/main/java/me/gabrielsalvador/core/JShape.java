package me.gabrielsalvador.core;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class JShape extends AbstractShape implements java.io.Serializable {

    private final PhysicsBodyComponent _owner;
    private transient Shape _shape;

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
    public float[] getBoundaries() {
        AABB aabb = new AABB();
        Transform transform = new Transform();
        _shape.computeAABB(aabb, transform, 0);
        Vec2 lowerBound = aabb.lowerBound;
        Vec2 upperBound = aabb.upperBound;
        return new float[]{lowerBound.x, lowerBound.y, upperBound.x, upperBound.y};
    }

    @Override
    public Vec2 getCenter() {
        if (_shape instanceof PolygonShape polygon) {
            Vec2 centroid = new Vec2(0, 0);
            float area = 0.0f;
            Vec2 refPoint = new Vec2(0, 0);
            float inv3 = 1.0f / 3.0f;

            for (int i = 0; i < polygon.m_count; ++i) {
                Vec2 p1 = refPoint;
                Vec2 p2 = polygon.m_vertices[i];
                Vec2 p3 = i + 1 < polygon.m_count ? polygon.m_vertices[i + 1] : polygon.m_vertices[0];

                Vec2 e1 = p2.sub(p1);
                Vec2 e2 = p3.sub(p1);

                float D = Vec2.cross(e1, e2);
                float triangleArea = 0.5f * D;
                area += triangleArea;

                // Area weighted centroid
                centroid.x += triangleArea * inv3 * (p1.x + p2.x + p3.x);
                centroid.y += triangleArea * inv3 * (p1.y + p2.y + p3.y);
            }

            centroid.x *= 1.0f / area;
            centroid.y *= 1.0f / area;
            return centroid;
        }
        // Handle other shapes (CircleShape, EdgeShape, ChainShape, etc.)
        return new Vec2(0, 0);
    }


    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Custom serialization logic for _shape
        if (_shape instanceof org.jbox2d.collision.shapes.CircleShape) {
            out.writeBoolean(true); // means its CircleShape
            out.writeFloat(_shape.m_radius);

        } else if (_shape instanceof PolygonShape polygon) {
            out.writeBoolean(false); //  PolygonShape
            out.writeInt(polygon.m_count);
            for (int i = 0; i < polygon.m_count; i++) {
                Vec2 vertex = polygon.m_vertices[i];
                out.writeFloat(vertex.x);
                out.writeFloat(vertex.y);
            }

        }

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Custom deserialization logic for _shape
        boolean isCircle = in.readBoolean();
        if (isCircle) {
            float radius = in.readFloat();
            _shape = new org.jbox2d.collision.shapes.CircleShape();
            (_shape).m_radius = radius;
        } else {

            int count = in.readInt();
            Vec2[] vertices = new Vec2[count];
            for (int i = 0; i < count; i++) {
                vertices[i] = new Vec2(in.readFloat(), in.readFloat());
            }
            PolygonShape polygon = new PolygonShape();
            polygon.set(vertices, count);
            _shape = polygon;
        }

    }
}
