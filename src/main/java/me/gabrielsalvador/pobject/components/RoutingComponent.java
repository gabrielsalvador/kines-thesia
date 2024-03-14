package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.common.SerializableRunnable;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.controllers.CommandController;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;

public class RoutingComponent extends Component {

    private PObject _target;
    private SerializableRunnable _pulseCallback;

    public RoutingComponent(PObject owner) {
        super(owner);
    }

    @Override
    public void display(PGraphics graphics) {
        if (_target == null) return;
        BodyComponent body = getOwner().getBodyComponent();

        Vec2 start = body.getPixelCenter();
        Vec2 end = _target.getBodyComponent().getPixelCenter();

        for (int i = 0; i < _subdivisions; i++) {
            float t = i / (float) (_subdivisions);
            float nextT = (i + 1) / (float) (_subdivisions);
            float x = PApplet.lerp(start.x, end.x, t);
            float y = PApplet.lerp(start.y, end.y, t);
            float nextX = PApplet.lerp(start.x, end.x, nextT);
            float nextY = PApplet.lerp(start.y, end.y, nextT);

            // Draw line segments
            graphics.line(x, y, nextX, nextY);


            graphics.fill(255, 0, 0);
            graphics.ellipseMode(PApplet.CENTER);
            if( i !=0 ) graphics.ellipse(x, y, 5, 5);
        }

        // draw an arrow to indicate the direction of the pulse
        Vec2 direction = end.sub(start);
        direction.normalize();
        direction.mulLocal(10);
        Vec2 arrowEnd = end.sub(direction);
        graphics.line(end.x, end.y, arrowEnd.x, arrowEnd.y);
        graphics.pushMatrix();
        graphics.translate(arrowEnd.x, arrowEnd.y);
        double angle = Math.cos(direction.x);
        graphics.rotate((float) angle);
        graphics.fill(255, 0, 0);
        graphics.triangle(0, 0, -5, -5, -5, 5);
        graphics.popMatrix();


    }


    @Override
    public String getName() {
        return "Routing";
    }

    @Override
    public void remove() {

    }


    private int _delay = 0;

    private int _subdivisions = 1;

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

    public void setPulseCallback(SerializableRunnable callback) {
        _pulseCallback = callback;
    }

    public void sendPulse(Object message) {
        if (_target == null ) return;
        RoutingComponent rc = _target.getRoutingComponent();
        if (rc == null)
            throw new RuntimeException("We're trying to send a pulse to an object that doesn't have a routing component\n" +
                "OBJ = " + _target.toString());

        rc.receivePulse(message);
    }

    private void receivePulse(Object message) {
        if (_pulseCallback != null) {
            _pulseCallback.run(message);
        }
    }
}
