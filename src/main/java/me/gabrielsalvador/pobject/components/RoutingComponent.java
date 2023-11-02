package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import processing.core.PApplet;
import processing.core.PGraphics;

public class RoutingComponent extends Component {

    private PObject _target;

    public RoutingComponent(PObject owner) {
        super(owner);
    }

    @Override
    public void display(PGraphics graphics) {
        if (_target == null) return;
        BodyComponent body = getOwner().getBodyComponent();

        float startX = body.getPixelPosition().x;
        float startY = body.getPixelPosition().y;
        float endX = _target.getBodyComponent().getPixelPosition().x;
        float endY = _target.getBodyComponent().getPixelPosition().y;

        for (int i = 0; i < _subdivisions; i++) {
            float t = i / (float) (_subdivisions);
            float nextT = (i + 1) / (float) (_subdivisions);
            float x = PApplet.lerp(startX, endX, t);
            float y = PApplet.lerp(startY, endY, t);
            float nextX = PApplet.lerp(startX, endX, nextT);
            float nextY = PApplet.lerp(startY, endY, nextT);

            // Draw line segments
            graphics.line(x, y, nextX, nextY);


            graphics.fill(255, 0, 0);
            graphics.ellipseMode(PApplet.CENTER);
            if( i !=0 ) graphics.ellipse(x, y, 5, 5);

        }
    }


    @Override
    public String getName() {
        return "Routing";
    }

    @Override
    public void remove() {

    }


    private int _delay = 0;

    private int _subdivisions = 0;

    @InspectableProperty(displayName = "Subdivisions")
    public int getSubdivisions() {
        return _subdivisions;
    }


    @InspectableProperty.SetterFor("Subdivisions")
    public void setSubdivisions(int subdivisions) {
        _subdivisions = subdivisions;
        System.out.println("Subdivisions set to " + subdivisions);
    }

    @InspectableProperty(displayName = "Delay")
    public int getDelay() {
        return _delay;
    }

    @InspectableProperty.SetterFor("Delay")
    public void setDelay(int delay) {
        _delay = delay;
        System.out.println("Delay set to " + delay);
    }


    @InspectableProperty(displayName = "Target")
    public void getTarget(PObject target) {
        _target = target;
    }

    @InspectableProperty.SetterFor("Target")
    public void setTarget(PObject target) {
        _target = target;
    }
}
