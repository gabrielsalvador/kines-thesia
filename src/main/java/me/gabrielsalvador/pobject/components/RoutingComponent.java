package me.gabrielsalvador.pobject.components;


import controlP5.DropdownList;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.ui.CodeEditor;
import me.gabrielsalvador.utils.CallbackWrapper;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.HashMap;
import java.util.Map;

public class RoutingComponent extends Component {

    private PObject _target;
    private int _subdivisions = 1;
    private CallbackWrapper _pulseCallback;
    @InspectableProperty(displayName = "Delay",controllerClass = DropdownList.class)
    private int _delay = 0;


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


        Vec2 direction = end.sub(start);
        direction.normalize();
        direction.mulLocal(10);
        Vec2 arrowEnd = end.sub(direction);
        graphics.line(end.x, end.y, arrowEnd.x, arrowEnd.y);

        graphics.pushMatrix();

        graphics.translate(arrowEnd.x, arrowEnd.y);
        double angle = Math.atan2(direction.y, direction.x);
        graphics.rotate((float) angle);
        graphics.fill(255, 0, 0);
        graphics.triangle(0, 0, -10, 5, -10, -5);

        graphics.popMatrix();


    }


    @Override
    public String getName() {
        return "Routing";
    }

    @Override
    public void dispose() {

    }

    @Override
    public void remove() {

    }

    @InspectableProperty(displayName = "Delay")
    public int getDelay() {
        return _delay;
    }





    @InspectableProperty(displayName = "onPulseReceived", controllerClass = CodeEditor.class)
    public CallbackWrapper getPulseCallback() {
        return _pulseCallback;
    }
    @InspectableProperty.SetterFor("onPulseReceived")
    public void setPulseCallback(CallbackWrapper callback) {
        _pulseCallback = callback;
    }




    @InspectableProperty(displayName = "Subdivisions")
    public int getSubdivisions() {
        return _subdivisions;
    }


    @InspectableProperty.SetterFor("Subdivisions")
    public void setSubdivisions(int subdivisions) {
        _subdivisions = subdivisions;
        System.out.println("Subdivisions set to " + subdivisions);
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



    public void sendPulse(Object message) {


        if (_target == null ) return;
        RoutingComponent rc = _target.getRoutingComponent();
        if (rc == null)
            throw new RuntimeException("We're trying to send a pulse to an object that doesn't have a routing component\n" +
                "OBJ = " + _target.toString());

        rc.receivePulse(message);
    }

    private void receivePulse(Object message) {

        Map<String, Object> scope = new HashMap<>();
        scope.put("message", message);
        scope.put("x", _owner.getBodyComponent().getPixelCenter().x);
        scope.put("y", _owner.getBodyComponent().getPixelCenter().y);

        if (_pulseCallback != null) {

            if (_pulseCallback.isKFunction()){
                KFunction kFunction = _pulseCallback.getKFunction();
                kFunction.execute(scope);
                return;
            } else if ( _pulseCallback.isRunnable() ){
                _pulseCallback.getRunnable().run();
                return;
            }


        }


    }
}
