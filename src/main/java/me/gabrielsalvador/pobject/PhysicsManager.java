package me.gabrielsalvador.pobject;



import me.gabrielsalvador.pobject.components.BodyData;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;


public class PhysicsManager {
    private static PhysicsManager _instance;
    private final Vec2 _gravity = new Vec2(0,9.8f);
    private final World _world = new World(_gravity);
    private PhysicsManager(){
        _world.setContactListener(new myContactListener());
    }
    public static PhysicsManager getInstance() {
        if (_instance == null) {
            _instance = new PhysicsManager();
        }

        return _instance;
    }

    public Body createCircle(Vec2 position, float radius) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC; 
        bodyDef.position.set(position);

        Body circleBody = _world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 10.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;

        // 6. Attach the shape to the body
        circleBody.createFixture(fixtureDef);

        return circleBody;
    }

    public void step(float timeStep, int velocityIterations, int positionIterations) {
        _world.step(timeStep, velocityIterations, positionIterations);
    }

    public World getWorld() {
        return _world;
    }


}

class myContactListener implements ContactListener{

    @Override
    public void beginContact(Contact contact) {


        PhysicsBodyComponent objA = (PhysicsBodyComponent) contact.getFixtureA().getUserData();
        PhysicsBodyComponent objB = (PhysicsBodyComponent) contact.getFixtureB().getUserData();
        if(objA == null || objB == null){
            return;
        }
        objA.onCollision(objB);
        objB.onCollision(objA);




    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
