package me.gabrielsalvador.pobject;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.*;
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
                    AppController.getInstance().queueModification(
                            () -> {
                                PObject p = new PObjectPreset.DropletPreset(pObject.getBodyComponent().getPosition()).create()[0];
                                AppController.getInstance().addPObject(p);
                            }
                    )
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


}
