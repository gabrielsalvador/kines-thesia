package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.pobject.views.CircleShape;
import me.gabrielsalvador.pobject.views.PolygonShape;
import me.gabrielsalvador.pobject.views.RectangleShape;
import me.gabrielsalvador.pobject.views.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import java.util.ArrayList;

public class PhysicsBodyComponent implements BodyComponent {
    private Body _body;
    public PhysicsBodyComponent(Vec2 position) {
        _body = PhysicsManager.getInstance().createCircle(position,5);
    }
    @Override
    public Vec2 getPosition() {
        return _body.getPosition();
    }

    @Override
    public BodyComponent setPosition(Vec2 position) {
        _body.setTransform(position, _body.getAngle());
        return this;
    }


    @Override
    public Shape getShape() {
        ShapeType type = _body.getFixtureList().getShape().getType();

        if (type == ShapeType.CIRCLE) {
            return new CircleShape(_body.getPosition(), _body.getFixtureList().getShape().getRadius());
        } else if (type == ShapeType.POLYGON) {
            org.jbox2d.collision.shapes.PolygonShape jboxPolygon = (org.jbox2d.collision.shapes.PolygonShape) _body.getFixtureList().getShape();
            PolygonShape polygon = new PolygonShape();
            for (int i = 0; i < jboxPolygon.getVertexCount(); i++) {
                Vec2 vertex = jboxPolygon.getVertex(i);
                polygon.addVertex(vertex.x, vertex.y);
            }
            return polygon;
        } else {

            return null;
        }
    }

    @Override
    public void setShape(Shape shape) {

    }

    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return "PhysicsBody";
    }

    @Override
    public ArrayList<PObjectProperty> getProperties() {
        //just a test
        PObjectProperty p = new PObjectProperty("test",int.class);
        ArrayList<PObjectProperty> list = new ArrayList<>();
        list.add(p);
        return list;

    }

    public Body getJBox2DBody(){
        return _body;
    }

    public void setLinearVelocity(Vec2 vec2) {
        _body.setLinearVelocity(vec2);
    }

    public void setType(BodyType bodyType) {
        _body.setType(bodyType);
    }
}
