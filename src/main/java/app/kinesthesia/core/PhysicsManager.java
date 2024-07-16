package app.kinesthesia.core;



import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.concurrent.locks.ReentrantLock;


public class PhysicsManager {
    private static final int Y_FLIP_INDICATOR = -1;
    int yFlip;// = Y_FLIP_INDICATOR; //flip y coordinate

    private static PhysicsManager _instance;
    private final Vec2 _gravity = new Vec2(0, 20.0f);
    private final World _world = new World(_gravity);

    public  int physicsFPS;
    private int physicsStepCounter = 0;
    private long lastCounterResetTime = System.nanoTime();

    private final ReentrantLock lock = new ReentrantLock();
    public final Object physicsThreadLock = new Object();

    // Variables to keep track of translating between world and screen coordinates
    float transX = 0.0f;
    float transY = 0.0f;
    float scaleFactor = 10.0f;


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

        circleBody.createFixture(fixtureDef);

        return circleBody;
    }

    public void removeBody(Body body) {
        _world.destroyBody(body);
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





    public void step(float timeStep, int velocityIterations, int positionIterations) {
        if (Clock.getInstance().getTransportState() == TransportState.PLAYING) {
            _world.step(timeStep, velocityIterations, positionIterations);
            physicsStepCounter++;

            long currentTime = System.nanoTime();

            if (currentTime - lastCounterResetTime >= 1_000_000_000) {
                physicsFPS = physicsStepCounter;
                physicsStepCounter = 0;
                lastCounterResetTime = currentTime;
            }
        }
    }

    public World getWorld() {
        return _world;
    }
    public void clearWorld() {
        Body body = _world.getBodyList();
        while (body != null) {
            Body nextBody = body.getNext();
            _world.destroyBody(body);
            body = nextBody;
        }
    }


    public float worldToPixelScale(float worldScale) {
        return worldScale * scaleFactor;
    }


    public ReentrantLock getLock() {
        return lock;
    }
}


