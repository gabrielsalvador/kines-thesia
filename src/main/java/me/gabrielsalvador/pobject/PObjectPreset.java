package me.gabrielsalvador.pobject;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.OnCollision;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.*;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;


public interface PObjectPreset {

    PObject[] create();


    class EmitterPreset implements PObjectPreset {


        private Vec2 _position = null;

        public EmitterPreset(Vec2 position) {
            _position = position;
        }

        public EmitterPreset() {
            this(new Vec2(0, 0));
        }

        @Override
        public PObject[] create() {
            //emitter obj
            PObject pObject = new PObject();

            //emitter body
            HologramBody bodyComponent = new HologramBody(pObject);
            bodyComponent.setPixelPosition(_position);
            bodyComponent.setView(new PEmitterView(bodyComponent));
            pObject.addComponent(BodyComponent.class, bodyComponent);

            //metronome obj
            PObject metronome = new PMetronome();

            //metronome body
            HologramBody metronomeBody = metronome.getBodyComponent();
            metronomeBody.setPixelPosition(_position.add(new Vec2(0, -20)));
            metronome.addComponent(BodyComponent.class, metronomeBody);


            //routing
            RoutingComponent routingComponent = new RoutingComponent(pObject);
            pObject.addComponent(RoutingComponent.class, routingComponent);
            RoutingComponent metronomeRoutingComponent = new RoutingComponent(metronome);
            metronome.addComponent(RoutingComponent.class, metronomeRoutingComponent);
            //
            metronome.getRoutingComponent().setTarget(pObject);
            pObject.getRoutingComponent().setPulseCallback(
                    () -> { // wrap in a runnable so that the value of pObject is not lost
                        AppController.getInstance().queueModification(
                                () -> {
                                    PObject p = new PObjectPreset.DropletPreset(pObject.getBodyComponent().getPosition()).create()[0];
                                    AppController.getInstance().addPObjectImmiadiately(p);
                                }
                        );
                    }
            );



            return new PObject[]{pObject, metronome};

        }


    }


    class DropletPreset implements PObjectPreset {

        //world position, not pixel
        private Vec2 _position = null;

        public DropletPreset(Vec2 position) {
            _position = position;
        }

        @Override
        public PObject[] create() {
            PObject droplet = new PObject();
            BodyData bodyData = BodyData.getDefaultBodyData();
            bodyData.circleRadius = 0.2f;
            bodyData.bodyType = BodyType.DYNAMIC;
            droplet.addComponent(BodyComponent.class, new PhysicsBodyComponent(droplet, bodyData));
            droplet.getBodyComponent().setPosition(_position);
            return new PObject[]{droplet};
        }


    }


    class ResonatorPreset implements PObjectPreset {

        private Vec2 _initialPosition = null;
        private Vec2 _finalPosition = null;
        private int _relativePitch = 0;


        /*
        / relativePitch is the degree relative to the root note
         */
        public ResonatorPreset(Vec2 _initialPosition, Vec2 _finalPosition, int relativePitch) {
            this._initialPosition = _initialPosition;
            this._finalPosition = _finalPosition;
            this._relativePitch = relativePitch;
        }

        @Override
        public PObject[] create() {
            float width = _finalPosition.sub(_initialPosition).length(); // Y is the height of the bar (in pixels
            float height = 20*((_relativePitch) % 8) +20; // X is the width of the bar

            // Create a new PObject
            PObject pObject1 = new PObject();
            BodyData bodyData = new BodyData();
            bodyData.shapeType = ShapeType.POLYGON;
            bodyData.bodyType = BodyType.STATIC;
            bodyData.vertices = PhysicsManager.getInstance().coordPixelsToWorld(
                    new Vec2[]{
                            new Vec2(0, 0),
                            new Vec2(width, 0),
                            new Vec2(width, height),
                            new Vec2(0, height)
                    }
            );

            PhysicsBodyComponent physicsBody = new PhysicsBodyComponent(pObject1, bodyData);
            physicsBody.setPixelPosition(new Vec2(_initialPosition.x, _initialPosition.y));

            // Calculate the angle for rotation
            float angle = (float) Math.atan2(_finalPosition.y - _initialPosition.y, _finalPosition.x - _initialPosition.x);
            physicsBody.setAngle(angle);

            pObject1.addComponent(BodyComponent.class, physicsBody);
            OnCollision onCollision = new OnCollision(pObject1);
            onCollision.setInterval(_relativePitch);
            pObject1.addComponent(OnCollision.class,onCollision);


            return new PObject[]{pObject1};
        }
    }
}
