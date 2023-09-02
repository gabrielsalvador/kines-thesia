package me.gabrielsalvador.pobject;



import me.gabrielsalvador.core.Sinesthesia;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PApplet;


public class PhysicsManager {
    private static final int Y_FLIP_INDICATOR = -1;
    PApplet parent = Sinesthesia.getInstance();
    private static PhysicsManager _instance;
    private final Vec2 _gravity = new Vec2(0,9.8f * 10);
    private final World _world = new World(_gravity);
    private PhysicsManager(){
        _world.setContactListener(new myContactListener());
    }

    // Variables to keep track of translating between world and screen coordinates
    float transX = 0.0f;
    float transY = 0.0f;
    float scaleFactor = 10.0f;
    int yFlip;// = Y_FLIP_INDICATOR; //flip y coordinate

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

        circleBody.createFixture(fixtureDef);

        return circleBody;
    }

    public Vec2 coordWorldToPixels(float worldX, float worldY) {
        float pixelX = worldX * scaleFactor + transX;
        float pixelY = worldY * scaleFactor + transY;
        if (yFlip == Y_FLIP_INDICATOR) pixelY = parent.height - pixelY;
        return new Vec2(pixelX, pixelY);
    }

    public Vec2 coordPixelsToWorld(float pixelX, float pixelY) {
        float worldX = (pixelX - transX) / scaleFactor;
        float worldY = (pixelY - transY) / scaleFactor;
        if (yFlip == Y_FLIP_INDICATOR) worldY = (parent.height - pixelY - transY) / scaleFactor;
        return new Vec2(worldX, worldY);
    }

    public void step(float timeStep, int velocityIterations, int positionIterations) {
        _world.step(timeStep, velocityIterations, positionIterations);
    }

    public World getWorld() {
        return _world;
    }


    public float worldToPixelScale(float worldScale) {
        return worldScale * scaleFactor;
    }
}

class myContactListener implements ContactListener{

    @Override
    public void beginContact(Contact contact) {

        PhysicsBodyComponent objA = (PhysicsBodyComponent) contact.getFixtureA().getBody().getUserData();
        PhysicsBodyComponent objB = (PhysicsBodyComponent) contact.getFixtureB().getBody().getUserData();
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
