package me.gabrielsalvador.pobject;



import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.components.OnCollision;
import me.gabrielsalvador.pobject.components.body.BodyData;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.sequencing.Clock;
import me.gabrielsalvador.sequencing.TransportState;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PApplet;


public class PhysicsManager {
    private static final int Y_FLIP_INDICATOR = -1;
    PApplet parent = Sinesthesia.getInstance();
    private static PhysicsManager _instance;
    private final Vec2 _gravity = new Vec2(0, 10.0f);
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

    public Body createPolygon(BodyData data) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = data.bodyType;
        bodyDef.position.set(data.x, data.y);
        Body body = _world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        Vec2[] vertices = new Vec2[data.vertices.length];
        for (int i = 0; i < data.vertices.length; i++) {
            vertices[i] = new Vec2(data.vertices[i].x, data.vertices[i].y);
        }
        polygonShape.set(vertices, vertices.length);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 10.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        return body;
    }

    public float scaleWorldToPixels(float worldValue) {
        return worldValue * scaleFactor;
    }
    public Vec2 coordWorldToPixels(float worldX, float worldY) {
        float pixelX = worldX * scaleFactor + transX;
        float pixelY = worldY * scaleFactor + transY;
        if (yFlip == Y_FLIP_INDICATOR) pixelY = parent.height - pixelY;
        return new Vec2(pixelX, pixelY);
    }

    public Vec2 coordWorldToPixels(Vec2 worldVertex) {
        return coordWorldToPixels(worldVertex.x, worldVertex.y);
    }

    public Vec2[] coordWorldToPixels(Vec2[] worldVertices) {
        Vec2[] pixelVertices = new Vec2[worldVertices.length];
        for (int i = 0; i < worldVertices.length; i++) {
            pixelVertices[i] = coordWorldToPixels(worldVertices[i].x, worldVertices[i].y);
        }
        return pixelVertices;
    }

    public Vec2 coordPixelsToWorld(float pixelX, float pixelY) {
        float worldX = (pixelX - transX) / scaleFactor;
        float worldY = (pixelY - transY) / scaleFactor;
        if (yFlip == Y_FLIP_INDICATOR) worldY = (parent.height - pixelY - transY) / scaleFactor;
        return new Vec2(worldX, worldY);
    }
    public Vec2[] coordPixelsToWorld(Vec2[] pixelVertices) {
        Vec2[] worldVertices = new Vec2[pixelVertices.length];
        for (int i = 0; i < pixelVertices.length; i++) {
            worldVertices[i] = coordPixelsToWorld(pixelVertices[i].x, pixelVertices[i].y);
        }
        return worldVertices;
    }

    public void step(float timeStep, int velocityIterations, int positionIterations) {

        if (Clock.getInstance().getTransportState() == TransportState.PLAYING) {
            _world.step(timeStep, velocityIterations, positionIterations);
        }

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

        PObject pObjectA = objA.getOwner();
        OnCollision onCollisionA = pObjectA.getComponent(OnCollision.class);
        if(onCollisionA != null){
            onCollisionA.onCollision(contact);
        }

        PObject pObjectB = objB.getOwner();
        OnCollision onCollisionB = pObjectB.getComponent(OnCollision.class);
        if(onCollisionB != null){
            onCollisionB.onCollision(contact);
        }




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
