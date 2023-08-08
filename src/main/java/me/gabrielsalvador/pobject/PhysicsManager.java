package me.gabrielsalvador.pobject;


import me.gabrielsalvador.Config;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;


public class PhysicsManager {
    private static PhysicsManager _instance;
    private final Vec2 _gravity = new Vec2(0,9.8f);
    private World _world = new World(_gravity);
    private PhysicsManager(){

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
        circleShape.setRadius(radius); // set radius of circle


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;

        // 6. Attach the shape to the body
        circleBody.createFixture(fixtureDef);

        return circleBody;
    }

    public void step(float timeStep, int velocityIterations, int positionIterations) {
        _world.step(timeStep, velocityIterations, positionIterations);
    }

}
