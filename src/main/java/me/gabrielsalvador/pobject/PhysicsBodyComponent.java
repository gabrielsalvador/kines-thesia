package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.pobject.components.BodyData;
import me.gabrielsalvador.pobject.views.CircleShape;
import me.gabrielsalvador.pobject.views.PolygonShape;
import me.gabrielsalvador.pobject.views.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import java.io.*;
import java.util.ArrayList;

public class PhysicsBodyComponent extends BodyComponent implements Serializable {

    transient private Body _body;
    /* used to serialize body data and recreate the body on deserialization */
    private BodyData _bodyData = new BodyData();

    @InspectableProperty
    private Runnable onCollision;

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

    public Body getJBox2DBody(){
        return _body;
    }

    public void setLinearVelocity(Vec2 vec2) {
        _body.setLinearVelocity(vec2);
    }

    public void setType(BodyType bodyType) {
        _body.setType(bodyType);
    }

    public void onCollision(PhysicsBodyComponent other) {
        System.out.println("Collision");
    }

    private void updateBodyData() {
        _bodyData.x = _body.getPosition().x;
        _bodyData.y = _body.getPosition().y;
        _bodyData.angle = _body.getAngle();
        _bodyData.linearVelocityX = _body.getLinearVelocity().x;
        _bodyData.linearVelocityY = _body.getLinearVelocity().y;
        _bodyData.angularVelocity = _body.getAngularVelocity();
        _bodyData.bodyType = _body.getType();

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        updateBodyData();
        out.defaultWriteObject();
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        recreateBody();
    }

    private void recreateBody() {

        _body = PhysicsManager.getInstance().createCircle(new Vec2(_bodyData.x, _bodyData.y), 5);
        _body.setTransform(new Vec2(_bodyData.x, _bodyData.y), _bodyData.angle);
        _body.setLinearVelocity(new Vec2(_bodyData.linearVelocityX, _bodyData.linearVelocityY));
        _body.setAngularVelocity(_bodyData.angularVelocity);
        _body.setType(_bodyData.bodyType);
    }
}
