package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import processing.core.PGraphics;

public class RoutingComponent extends Component{
    @Override
    public void display(PGraphics graphics) {

    }

    @Override
    public String getName() {
        return "Routing";
    }

    @Override
    public void remove() {

    }

    public enum RouterType{
        ENDPOINT,
        CONNECTION
    }




    private  int _delay = 0;

    @InspectableProperty(displayName = "Subdivisions")
    private int _subdivisions = 0;

    public RoutingComponent(PObject owner){
        super(owner);
    }


    @InspectableProperty.SetterFor("Subdivisions")
    public void setSubdivisions(int subdivisions){
        _subdivisions = subdivisions;
        System.out.println("Subdivisions set to " + subdivisions);
    }

    @InspectableProperty(displayName = "Delay")
    public int getDelay(){
        return _delay;
    }
    @InspectableProperty.SetterFor("Delay")
    public void setDelay(int delay){
        _delay = delay;
        System.out.println("Delay set to " + delay);
    }
}
