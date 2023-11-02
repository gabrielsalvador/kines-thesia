package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.components.body.PEmitterView;
import org.jbox2d.common.Vec2;


public interface PObjectPreset {


    PObject[] create();




    class EmitterPreset implements PObjectPreset {



        private Vec2 _position = null;

        public EmitterPreset(Vec2 position) {
            _position = position;
        }
        public EmitterPreset() {
            this(new Vec2(0,0));
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


            metronome.getRoutingComponent().setTarget(pObject);


            return new PObject[]{pObject, metronome};

        }


    }

}
