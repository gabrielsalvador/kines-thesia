package app.kinesthesia.gui.processing.views;

import app.kinesthesia.core.BodyComponent;
import app.kinesthesia.core.Component;
import app.kinesthesia.core.MathUtils;
import app.kinesthesia.core.PhysicsBodyComponent;
import app.kinesthesia.gui.processing.ProcessingGuiMain;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

import static processing.core.PApplet.dist;

public class PRubberbandView extends PhysicsBodyView {

    PhysicsBodyComponent _owner;
    PGraphics graphics = ProcessingGuiMain.getInstance().getGraphics();

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

        Vec2 pixelPosition = _owner.getPosition();
        graphics.pushMatrix();
        graphics.translate(pixelPosition.x, pixelPosition.y);
        graphics.rotate(_owner.getAngle());

        graphics.fill(127 + _brightness);

        Vec2 vertexA = polygon.m_vertices[1];
        Vec2 A = MathUtils.coordWorldToPixels(vertexA.x, vertexA.y);

        Vec2 vertexB = polygon.m_vertices[2];
        Vec2 B = MathUtils.coordWorldToPixels(vertexB.x, vertexB.y);

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
        Vec2 A = MathUtils.coordWorldToPixels(vertexA.x, vertexA.y);

        Vec2 vertexB = polygon.m_vertices[2];
        Vec2 B = MathUtils.coordWorldToPixels(vertexB.x, vertexB.y);

        Vec2 pixelPosition = _owner.getPosition();
        float x1 = pixelPosition.x + A.x;
        float y1 = pixelPosition.y + A.y;
        float x2 = pixelPosition.x + B.x;
        float y2 = pixelPosition.y + B.y;



        graphics.stroke(255, 0, 0);
        graphics.line(x1, y1, x2, y2);
        graphics.ellipse(mouseX, mouseY, 10, 10);


        return MathUtils.isPointOverLineSegment(mouseX, mouseY, x1, y1, x2, y2, 10);
    }




    @Override
    public void onBeginContact(PhysicsBodyComponent other) {
        _brightness = 20;
    }

}