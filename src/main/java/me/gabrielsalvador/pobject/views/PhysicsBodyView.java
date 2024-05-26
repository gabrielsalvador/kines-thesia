package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import processing.core.PGraphics;
import me.gabrielsalvador.utils.MathUtils;

import static processing.core.PApplet.nf;

public class PhysicsBodyView implements View<Component> {
    private final PhysicsBodyComponent model;
    private final PhysicsManager pm = PhysicsManager.getInstance();
    protected float _brightness = 0;

    public PhysicsBodyView(PhysicsBodyComponent model) {
        this.model = model;
    }

    @Override
    public Component getModel() {
        return model;
    }

    @Override
    public void display(PGraphics graphics, Component model) {
        Body body = this.model.getJBox2DBody();
        if(body == null || body.getFixtureList() == null) return;

        Shape shape = body.getFixtureList().getShape();

        switch (shape.getType()) {
            case CIRCLE:
                Vec2 pixelPosition = pm.coordWorldToPixels(body.getPosition().x, body.getPosition().y);
                graphics.ellipseMode(graphics.CENTER);
                float radiusPixels = pm.scaleWorldToPixels(shape.getRadius());
                graphics.ellipse(pixelPosition.x, pixelPosition.y, radiusPixels * 2, radiusPixels * 2);
                break;

            case POLYGON:
                PolygonShape polygon = (PolygonShape) shape;
                graphics.pushMatrix();
                pixelPosition = this.model.getPixelPosition();
                graphics.translate(pixelPosition.x, pixelPosition.y);
                graphics.rotate(body.getAngle());

                graphics.fill(255,255,255, _brightness); // Example: white color

                graphics.beginShape();

                for (int i = 0; i < 4 ; i++) {

                    Vec2 vertex = polygon.m_vertices[i];
                    Vec2 pixelVertex = pm.coordWorldToPixels(vertex.x, vertex.y);
                    graphics.vertex(pixelVertex.x, pixelVertex.y);

                }
                graphics.endShape(graphics.CLOSE);

                graphics.popMatrix();

                //DEBUG - write vertices coordinates
//                graphics.pushMatrix();
//                graphics.translate(pixelPosition.x, pixelPosition.y);
//
//                graphics.fill(0);
//                for (int i = 0; i < 4 ; i++) {
//                    Vec2 vertex = PhysicsManager.getInstance().coordWorldToPixels(polygon.m_vertices[i].x, polygon.m_vertices[i].y);
//                    float angle = body.getAngle();
//                    float vertexX = vertex.x * (float) Math.cos(angle) - vertex.y * (float) Math.sin(angle);
//                    float vertexY = vertex.x * (float) Math.sin(angle) + vertex.y * (float) Math.cos(angle);
//                    graphics.textSize(12);
//                    graphics.text((int)vertexX + ", " + (int)vertexY, vertexX, vertexY);
//                }
//                graphics.popMatrix();

                break;

            default:
                break;
        }

        if (_brightness > 0) {
            _brightness -= 20;
        }
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        Body body = this.model.getJBox2DBody();
        if(body == null || body.getFixtureList() == null) return false;

        Shape shape = body.getFixtureList().getShape();
        Vec2 bodyPixelPos = pm.coordWorldToPixels(body.getPosition().x, body.getPosition().y).add(this.model.getPixelPosition());

        if (shape.getType() == ShapeType.CIRCLE) {
            return isMouseOverCircle(mouseX, mouseY, bodyPixelPos, shape.getRadius());
        }

        if (shape.getType() == ShapeType.POLYGON) {
            return isMouseOverPolygon(mouseX, mouseY, (PolygonShape) shape, body.getAngle(), body.getPosition());
        }

        return false;
    }

    private boolean isMouseOverCircle(int mouseX, int mouseY, Vec2 bodyPixelPos, float radius) {
        return (mouseX >= bodyPixelPos.x - radius && mouseX <= bodyPixelPos.x + radius) &&
                (mouseY >= bodyPixelPos.y - radius && mouseY <= bodyPixelPos.y + radius);
    }

    private boolean isMouseOverPolygon(int mouseX, int mouseY, PolygonShape polygon, float angle, Vec2 bodyPos) {
        boolean inside = false;
        Vec2 vertexI, vertexJ, viPixelSpace, vjPixelSpace;

        for (int i = 0, j = polygon.getVertexCount() - 1; i < polygon.getVertexCount(); j = i++) {
            vertexI = MathUtils.rotatePoint(0, 0, angle, polygon.m_vertices[i]).add(bodyPos);
            vertexJ = MathUtils.rotatePoint(0, 0, angle, polygon.m_vertices[j]).add(bodyPos);
            viPixelSpace = pm.coordWorldToPixels(vertexI.x, vertexI.y);
            vjPixelSpace = pm.coordWorldToPixels(vertexJ.x, vertexJ.y);

            if ((viPixelSpace.y > mouseY) != (vjPixelSpace.y > mouseY) && (mouseX < (vjPixelSpace.x - viPixelSpace.x) * (mouseY - viPixelSpace.y) / (vjPixelSpace.y - viPixelSpace.y) + viPixelSpace.x)) {
                inside = !inside;
            }
        }
        return inside;
    }


    public void onBeginContact(PhysicsBodyComponent other) {
        _brightness = 255;
    }
}
