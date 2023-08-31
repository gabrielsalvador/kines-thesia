package me.gabrielsalvador.pobject;



import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.components.BodyData;
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
    float scaleFactor = 200.0f;
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

        // 6. Attach the shape to the body
        circleBody.createFixture(fixtureDef);

        return circleBody;
    }

    public Vec2 coordWorldToPixels(float worldX, float worldY) {

        float pixelX = PApplet.map(worldX, 0.0f, 1.0f, transX, transX+scaleFactor);
        float pixelY = PApplet.map(worldY, 0.0f, 1.0f, transY, transY+scaleFactor);
        if (yFlip == Y_FLIP_INDICATOR) pixelY = PApplet.map(pixelY,0.0f,parent.height, parent.height,0.0f);
        return new Vec2(pixelX, pixelY);
    }

    public Vec2 coordPixelsToWorld(float pixelX, float pixelY) {
        float worldX = PApplet.map(pixelX, transX, transX+scaleFactor, 0.0f, 1.0f);
        float worldY = pixelY;
        if (yFlip == Y_FLIP_INDICATOR) {
            worldY = PApplet.map(pixelY, parent.height, 0.0f, 0.0f, parent.height);
        }
        worldY = PApplet.map(worldY, transY, transY+scaleFactor, 0.0f, 1.0f);
        return new Vec2(worldX, worldY);
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
