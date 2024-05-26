package me.gabrielsalvador.pobject;

import me.gabrielsalvador.kinescript.lang.Kinescript;
import me.gabrielsalvador.pobject.components.PlayNoteOnCollision;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.BodyData;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.components.musicalnote.MusicalNoteComponent;
import me.gabrielsalvador.pobject.views.PRubberbandView;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class PRubberbandPreset implements PObjectPreset{

    private Vec2 _initialPosition;
    private Vec2 _finalPosition;
    private int interval;

    public PRubberbandPreset(Vec2 initialPosition, Vec2 finalPosition, int interval){
        _initialPosition = initialPosition;
        _finalPosition = finalPosition;
        this.interval = interval;
    }

    @Override
    public PObject[] create() {

        float width = _finalPosition.clone().sub(_initialPosition).length(); // Width of the bar
        float height = 2 ; // Height of the bar

        // Center offset
        float halfWidth = width / 2;
        float halfHeight = height / 2;

        // Create a new PObject
        PObject pObject1 = new PObject();
        BodyData bodyData = new BodyData();
        bodyData.isBullet = true;
        bodyData.isSensor = true;
        bodyData.shapeType = ShapeType.POLYGON;
        bodyData.bodyType = BodyType.STATIC;
        bodyData.vertices = PhysicsManager.getInstance().coordPixelsToWorld(
                new Vec2[]{
                        new Vec2(-halfWidth, -halfHeight),
                        new Vec2(halfWidth, -halfHeight),
                        new Vec2(halfWidth, halfHeight),
                        new Vec2(-halfWidth, halfHeight)
                }
        );

        PhysicsBodyComponent physicsBody = new PhysicsBodyComponent(pObject1, bodyData);
        physicsBody.setView(new PRubberbandView(physicsBody));
        Body body = physicsBody.getJBox2DBody();
//        body.setBullet(true);
//        for (Fixture fixture = body.getFixtureList(); fixture != null; fixture = fixture.getNext()) {
//            fixture.setSensor(true);
//        }


        physicsBody.setPixelPosition(_initialPosition.add(_finalPosition).mul(0.5f));

        // Calculate the angle for rotation
        float angle = (float) Math.atan2(_finalPosition.y - _initialPosition.y, _finalPosition.x - _initialPosition.x);
        physicsBody.setAngle(angle);

        pObject1.addComponent(BodyComponent.class, physicsBody);
        PlayNoteOnCollision onCollision = new PlayNoteOnCollision(pObject1);


        pObject1.addComponent(PlayNoteOnCollision.class, onCollision);

        // add a musical note to the resonator

        MusicalNoteComponent musicalNoteComponent = new MusicalNoteComponent(pObject1, interval);
        pObject1.addComponent(MusicalNoteComponent.class, musicalNoteComponent);


        return new PObject[]{pObject1};

    }
}
