package me.gabrielsalvador.pobject.views;

import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class PolygonShape extends Shape {
    private List<PVector> vertices;

    public PolygonShape() {
        this.vertices = new ArrayList<>();
    }

    public void addVertex(float x, float y) {
        vertices.add(new PVector(x, y));
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float x, float y) {
        int numIntersections = 0;
        for (int i = 0, j = vertices.size() - 1; i < vertices.size(); j = i++) {
            PVector vertexI = vertices.get(i);
            PVector vertexJ = vertices.get(j);

            if (((vertexI.y > mouseY) != (vertexJ.y > mouseY)) &&
                    (mouseX < (vertexJ.x - vertexI.x) * (mouseY - vertexI.y) / (vertexJ.y - vertexI.y) + vertexI.x)) {
                numIntersections++;
            }
        }
        return numIntersections % 2 == 1;  // Odd number of intersections means the point is inside the polygon
    }

    @Override
    public void display(PGraphics graphics, float x, float y) {
        graphics.beginShape();
        for (PVector vertex : vertices) {
            graphics.vertex(vertex.x + x, vertex.y + y);
        }
        graphics.endShape(PGraphics.CLOSE);
    }

    @Override
    public float[] getBoundayBox() {
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;
        for (PVector vertex : vertices) {
            if (vertex.x < minX) minX = vertex.x;
            if (vertex.y < minY) minY = vertex.y;
            if (vertex.x > maxX) maxX = vertex.x;
            if (vertex.y > maxY) maxY = vertex.y;
        }
        return new float[]{minX, minY, maxX, maxY};
    }


}
