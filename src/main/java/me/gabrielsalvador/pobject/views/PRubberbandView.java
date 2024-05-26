package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.utils.MathUtils;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

import static processing.core.PApplet.dist;

public class PRubberbandView extends PhysicsBodyView {

    PhysicsBodyComponent _owner;



    public PRubberbandView(BodyComponent owner) {
        super((PhysicsBodyComponent) owner);
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

        Vec2 pixelPosition = _owner.getPixelPosition();
        graphics.pushMatrix();
        graphics.translate(pixelPosition.x, pixelPosition.y);
        graphics.rotate(_owner.getAngle());

        graphics.fill(127 + _brightness);

        Vec2 vertexA = polygon.m_vertices[1];
        Vec2 A = PhysicsManager.getInstance().coordWorldToPixels(vertexA.x, vertexA.y);

        Vec2 vertexB = polygon.m_vertices[2];
        Vec2 B = PhysicsManager.getInstance().coordWorldToPixels(vertexB.x, vertexB.y);

        int resolution = 4;

        Vec2 previousPoint = null;
        for (int i = 0; i <= resolution; i++) {
            float t = (float) i / resolution;

            Vec2 point = new Vec2(A.x * (1 - t) + B.x * t, A.y * (1 - t) + B.y * t);

            int time = (int) System.currentTimeMillis() ;

            point.y += _brightness * (Math.sin(time) ) * Math.sin( (float)Math.PI * i/(float)resolution )  ;

            // Draw the line segment
            if (previousPoint != null) {
                graphics.line(previousPoint.x, previousPoint.y, point.x, point.y);
            }

            previousPoint = point;
        }

        graphics.popMatrix();

        if (_brightness > 0) {
            _brightness -= 1;
        } else {
            _brightness = 0;
        }
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {

        Shape shape = _owner.getJBox2DBody().getFixtureList().getShape();
        PolygonShape polygon = (PolygonShape) shape;

        Vec2 vertexA = polygon.m_vertices[1];
        Vec2 A = PhysicsManager.getInstance().coordWorldToPixels(vertexA.x, vertexA.y);

        Vec2 vertexB = polygon.m_vertices[2];
        Vec2 B = PhysicsManager.getInstance().coordWorldToPixels(vertexB.x, vertexB.y);

        // Check if the mouse is over the line segment AB with a 10 pixel margin
        return MathUtils.isPointOverLineSegment(mouseX, mouseY, A.x, A.y, B.x, B.y, 100);
    }




    @Override
    public void onBeginContact(PhysicsBodyComponent other) {
        _brightness = 20;
    }

}