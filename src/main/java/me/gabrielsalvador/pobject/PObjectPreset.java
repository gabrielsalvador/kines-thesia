package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.components.body.HologramBodyView;
import me.gabrielsalvador.pobject.components.body.PEmitterView;
import org.jbox2d.common.Vec2;

import java.util.HashMap;
import java.util.Map;


public interface PObjectPreset {


    PObject[] create();




    public class EmitterPreset implements PObjectPreset {



        private Vec2 _position = null;

        public EmitterPreset(Vec2 position) {
            _position = position;
        }
        public EmitterPreset() {
            this(new Vec2(0,0));
        }

        @Override
        public PObject[] create() {
            PObject pObject = new PObject();

            //body
            HologramBody bodyComponent = new HologramBody(pObject);
            bodyComponent.setPixelPosition(_position);
            bodyComponent.setView(new PEmitterView(bodyComponent));
            pObject.addComponent(BodyComponent.class, bodyComponent);

            //routing
            pObject.addComponent(RoutingComponent.class, new RoutingComponent(pObject));

            return new PObject[]{pObject};

        }


    }

}
