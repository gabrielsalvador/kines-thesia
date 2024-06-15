package me.gabrielsalvador.pobject;


import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import me.gabrielsalvador.pobject.components.PlayNoteOnCollision;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.*;
import me.gabrielsalvador.pobject.components.musicalnote.MusicalNoteComponent;
import me.gabrielsalvador.pobject.views.PKeyboardView;
import me.gabrielsalvador.utils.CallbackWrapper;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;


public interface PObjectPreset {

    static PObjectPreset getPresetByName(String presetName) {
        return switch (presetName) {
            case "emitter" -> new EmitterPreset();
            case "droplet" -> new DropletPreset(new Vec2(0, 0));
            case "resonator" -> new ResonatorPreset(new Vec2(0, 0), new Vec2(0, 0), 0);
            case "keyboard" -> new KeyboardPreset(new Vec2(0, 0));
            default -> null;
        };
    }

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
            PObject emitter = new PObject();

            //emitter body
            HologramBody emitterBody = new HologramBody(emitter);
            emitterBody.setPixelPosition(_position);
            emitterBody.setView(new PEmitterView(emitterBody));
            emitter.addComponent(BodyComponent.class, emitterBody);

            //metronome obj
            PObject metronome = new PMetronome();

            //metronome body
            HologramBody metronomeBody = metronome.getBodyComponent();
            metronomeBody.setPixelPosition(_position.add(new Vec2(0, -20)));
            metronome.addComponent(BodyComponent.class, metronomeBody);


            //routing
            RoutingComponent emitterRouting = new RoutingComponent(emitter);
            emitterRouting.setPulseCallback(
                    new CallbackWrapper(Kinescript.compileFunction("add(\"droplet\", x, y)")
            ));
            emitter.addComponent(RoutingComponent.class, emitterRouting);
            RoutingComponent metronomeRoutingComponent = new RoutingComponent(metronome);
            metronome.addComponent(RoutingComponent.class, metronomeRoutingComponent);

            metronome.getRoutingComponent().setTarget(emitter);

            return new PObject[]{emitter, metronome};

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
            bodyData.isBullet = true;
            bodyData.circleRadius = 0.2f;
            bodyData.bodyType = BodyType.DYNAMIC;
            droplet.addComponent(BodyComponent.class, new PhysicsBodyComponent(droplet, bodyData));
            PhysicsBodyComponent bodyComponent = (PhysicsBodyComponent) droplet.getBodyComponent();
            bodyComponent.setPosition(_position);
            bodyComponent.getJBox2DBody().setBullet(true);
//            ((PhysicsBodyComponent)droplet.getBodyComponent()).getJBox2DBody().applyLinearImpulse(new Vec2(0, 10f), _position);

            return new PObject[]{droplet};
        }


    }


    class ResonatorPreset implements PObjectPreset {

        private Vec2 _initialPosition = null;
        private Vec2 _finalPosition = null;
        private int interval = 0;


        /*
        / relativePitch is the degree relative to the root note
         */
        public ResonatorPreset(Vec2 _initialPosition, Vec2 _finalPosition, int interval) {
            this._initialPosition = _initialPosition;
            this._finalPosition = _finalPosition;
            this.interval = interval;
        }


        @Override
        public PObject[] create() {
            float width = _finalPosition.clone().sub(_initialPosition).length(); // Width of the bar
            float height = 20; // Height of the bar

            // Center offset
            float halfWidth = width / 2;
            float halfHeight = height / 2;

            // Create a new PObject
            PObject pObject1 = new PObject();
            BodyData bodyData = new BodyData();
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

    class KeyboardPreset implements PObjectPreset {

        private Vec2 _position = null;

        public KeyboardPreset(Vec2 position) {
            _position = position;
        }

        @Override
        public PObject[] create() {
            PKeyboard pKeyboard = new PKeyboard();

            RoutingComponent routingComponent = new RoutingComponent(pKeyboard);
            pKeyboard.addComponent(RoutingComponent.class, routingComponent);

            HologramBody body = new HologramBody(pKeyboard);
            pKeyboard.addComponent(BodyComponent.class, body);

            body.setView(new PKeyboardView(body));
            body.setPixelPosition(_position);
            return new PObject[]{pKeyboard};
        }
    }
}
