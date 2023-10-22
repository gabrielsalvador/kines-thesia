package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.Component;

import java.util.ArrayList;
import java.util.Map;

public class PObjectPreset {

    private ArrayList<Class<? extends Component>> components = new ArrayList<>();

    public ArrayList<Class<? extends Component>> getComponentList(){
        return components;
    }
}
