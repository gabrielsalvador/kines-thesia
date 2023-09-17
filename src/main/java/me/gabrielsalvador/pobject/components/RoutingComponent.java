package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.pobject.PObject.InspectableProperty;

public class RoutingComponent extends Component{
    public enum RouterType{
        ENDPOINT,
        CONNECTION
    }

    private final RouterType _routerType;

    @InspectableProperty
    private final int _delay = 0;

    @InspectableProperty
    private final int subdivisions = 0;

    public RoutingComponent(RouterType routerType){
        _routerType = routerType;
    }
}
