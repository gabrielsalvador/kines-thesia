package me.gabrielsalvador.pobject.components.body;

import me.gabrielsalvador.audio.AudioManager;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.MusicalNoteComponent;
import me.gabrielsalvador.pobject.components.body.shape.AbstractShape;
import me.gabrielsalvador.pobject.components.body.shape.JShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import java.io.*;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;

public class PhysicsBodyComponent extends BodyComponent implements Serializable {

    transient private Body _body;
    private JShape _shape;
    /* used to serialize body data and recreate the body on deserialization */
    private BodyData _bodyData = new BodyData();

    @InspectableProperty
    private MusicalNoteComponent _onColision;


    public PhysicsBodyComponent(PObject owner, Vec2 position) {
        super(owner);
        createBody();
        setPixelPosition(position);
    }
    public PhysicsBodyComponent(PObject owner,BodyData bodyData) {
        super(owner);
        _bodyData = bodyData;
        createBody();
    }



    @Override
    public Vec2 getPosition() {
        return _body.getPosition();
    }

    @Override
    public BodyComponent setPosition(Vec2 position) {
        _body.setTransform(position, _body.getAngle());
        _position = position;
        return this;
    }

  //function to set the position of the body in pixels
    public BodyComponent setPixelPosition(Vec2 position) {
        Vec2 worldCoords = PhysicsManager.getInstance().coordPixelsToWorld(position.x, position.y);
        _bodyData.x = worldCoords.x;
        _bodyData.y = worldCoords.y;
        _body.setTransform(worldCoords, _body.getAngle());
        setPosition(worldCoords);
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
    public void display() {

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
        if (_onColision != null) {
            String sampleName = _onColision.getSampleName();
            if (sampleName != null && !sampleName.isEmpty()) {
                AudioManager.getInstance().play(sampleName);
            }
        }
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
        createBody();
    }

    //we need to recreate the body on deserialization
    private void createBody() {
        switch (_bodyData.shapeType) {
            case CIRCLE:
                _body = PhysicsManager.getInstance().createCircle(new Vec2(_bodyData.x, _bodyData.y), 1);
                break;
            case POLYGON:
                _body = PhysicsManager.getInstance().createPolygon(_bodyData);
                break;
        }
        _shape = new JShape(_body.getFixtureList().getShape(),this);
        _body.setTransform(new Vec2(_bodyData.x, _bodyData.y), _bodyData.angle);
        _body.setLinearVelocity(new Vec2(_bodyData.linearVelocityX, _bodyData.linearVelocityY));
        _body.setAngularVelocity(_bodyData.angularVelocity);
        _body.setType(_bodyData.bodyType);
        _body.setUserData(this);


    }

    public void moveByPixels(Vec2 displacement) {
        Vec2 worldCoords = PhysicsManager.getInstance().coordPixelsToWorld(displacement.x, displacement.y);
        getPosition().addLocal(worldCoords);
        _body.setTransform(getPosition(), _body.getAngle());
    }

    public float getAngle() {
        return _body.getAngle();
    }
}
