package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import java.util.HashMap;
import java.util.Map;


public interface PObjectPreset {



    Map<Class<? extends Component>, Class<? extends Component>> getComponentList() ;


    public class EmitterPreset implements PObjectPreset {

        // this is a map of component class -> component instance
        // e.g. BodyComponent.class -> new HologramBody()
        private final Map<Class<? extends Component>, Class<? extends Component>> componentsMap = new HashMap<>();


        public EmitterPreset() {
            componentsMap.put(BodyComponent.class, HologramBody.class);
        }

        @Override
        public Map<Class<? extends Component>, Class<? extends Component>>  getComponentList() {
            return componentsMap;
        }
    }

}
