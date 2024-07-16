package app.kinesthesia.core;


import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PhysicsBodyComponent extends BodyComponent implements Serializable {

    transient private Body _body;

    /* used to serialize body data and recreate the body on deserialization */
    private BodyData _bodyData = null;


    @PObject.InspectableProperty(displayName = "Mass")
    private float _mass = 1;

    public PhysicsBodyComponent(PObject owner, BodyData _bodyData) {
        super(owner);
        this._bodyData = _bodyData;
        createBody();
    }


    public PhysicsBodyComponent(PObject owner, Vec2 position) {
        this(owner, BodyData.getDefaultBodyData());

    }


    @Override
    public Vec2 getPosition() {
        return _body.getPosition();
    }


    public BodyComponent setPosition(Vec2 position) {
        _body.setTransform(position, _body.getAngle());
        return this;
    }


    @Override
    public AbstractShape getShape() {
        return _shape;
    }

    @Override
    public void setShape(AbstractShape shape) {

    }

    @Override
    public void update() {

    }


    @Override
    public String getName() {
        return "PhysicsBody";
    }

    @Override
    public void dispose() {
        PhysicsManager.getInstance().removeBody(getJBox2DBody());
    }

    public Body getJBox2DBody() {
        return _body;
    }

    public void setLinearVelocity(Vec2 vec2) {
        _body.setLinearVelocity(vec2);
    }

    public void setType(BodyType bodyType) {
        _body.setType(bodyType);

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

    private BodyData getBodyData() {
        return _bodyData;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        updateBodyData();
        out.defaultWriteObject();
    }

    @Override
    public void remove() {
        PhysicsManager.getInstance().getWorld().destroyBody(_body);
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        createBody();
    }

    //we need to recreate the body on deserialization
    //this function creates the body based on the data stored in the _bodyData variable
    private void createBody() {
        switch (_bodyData.shapeType) {
            case CIRCLE:
                _body = PhysicsManager.getInstance().createCircle(new Vec2(_bodyData.x, _bodyData.y), _bodyData.circleRadius);
                break;
            case POLYGON:
                _body = PhysicsManager.getInstance().createPolygon(_bodyData);
                break;
        }
        _shape = new JShape(_body.getFixtureList().getShape(), this);
        _body.setTransform(new Vec2(_bodyData.x, _bodyData.y), _bodyData.angle);
        _body.setLinearVelocity(new Vec2(_bodyData.linearVelocityX, _bodyData.linearVelocityY));
        _body.setAngularVelocity(_bodyData.angularVelocity);
        _body.setType(_bodyData.bodyType);
        _body.setBullet(_bodyData.isBullet);
        // set is sensor
        for (Fixture fixture = _body.getFixtureList(); fixture != null; fixture = fixture.getNext()) {
            fixture.setSensor(_bodyData.isSensor);
        }
        _body.setUserData(this);


    }




    @PObject.InspectableProperty(displayName = "Static")
    public boolean getIsStatic() {
        return _body.getType() == BodyType.STATIC;
    }

    @PObject.InspectableProperty.SetterFor("Static")
    public void setIsStatic(boolean isStatic) {


        if (isStatic) {
            _body.setType(BodyType.STATIC);
        } else {
            _body.setType(BodyType.DYNAMIC);
        }
    }

    @PObject.InspectableProperty.SetterFor("Mass")
    public void setMass(float mass) {
        _mass = mass;
        MassData massData = new MassData();
        _body.getMassData(massData);
        massData.mass = mass;
        _body.setMassData(massData);
    }

    @Override
    public float getAngle() {
        return _body.getAngle();
    }

    @Override
    public void setVelocity(Vec2 vec2) {
        _body.setLinearVelocity(vec2);
    }

    public void setAngle(float angle) {
        _body.setTransform(_body.getPosition(), angle);
    }

    @Override
    public void rotateBodyAroundPivot( Vec2 pivot, float angle) {

        float angleRadians = (float) Math.toRadians(angle);


        Vec2 bodyPosition = _body.getPosition();
        Vec2 translationToPivot = pivot.sub(bodyPosition);
        _body.setTransform(bodyPosition.add(translationToPivot), _body.getAngle());


        _body.setTransform(_body.getPosition(), angleRadians);


        Vec2 translationBack = _body.getPosition().sub(translationToPivot);
        _body.setTransform(translationBack, _body.getAngle());
    }

    public void rotateBy(float angle) {
        _body.setTransform(_body.getPosition(), _body.getAngle() + angle);
    }


    @Override
    public void setTransform(Vec2 position, float angle) {
        _body.setTransform(position, angle);
    }



    public void onBeginContact(PhysicsBodyComponent other){

    }

}
