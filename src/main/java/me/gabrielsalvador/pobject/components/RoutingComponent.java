package me.gabrielsalvador.pobject.components;


import controlP5.DropdownList;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.pobject.PMetronome;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.musicalnote.MusicalNoteComponent;
import me.gabrielsalvador.utils.CallbackWrapper;
import me.gabrielsalvador.utils.MusicalNote;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class RoutingComponent extends Component {

    private ArrayList<PObject> _targets = new ArrayList<>();
    private int _subdivisions = 1;
    private CallbackWrapper _pulseCallback;
    @InspectableProperty(displayName = "Delay",controllerClass = DropdownList.class)
    private int _delay = 0;


    public RoutingComponent(PObject owner) {
        super(owner);
    }

    @Override
    public void display(PGraphics graphics) {
        for (PObject target : _targets){
            if (target == null) return;
        BodyComponent body = getOwner().getBodyComponent();

        Vec2 start = body.getPixelCenter();
        Vec2 end = target.getBodyComponent().getPixelCenter();

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

        // Draw arrow
        Vec2 direction = end.sub(start);
        direction.normalize();
        direction.mulLocal(10);
        Vec2 middlePoint = start.add(end).mul(0.5f).add(direction);
        graphics.line(end.x, end.y, middlePoint.x, middlePoint.y);

        graphics.pushMatrix();
        graphics.translate(middlePoint.x, middlePoint.y);
        double angle = Math.atan2(direction.y, direction.x);
        graphics.rotate((float) angle);
        graphics.fill(Config.APP_BACKGROUND_COLOR[0], Config.APP_BACKGROUND_COLOR[1], Config.APP_BACKGROUND_COLOR[2]);
        graphics.triangle(-5, -5, 5, 0, -5, 5);
        graphics.popMatrix();
        }
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





    @InspectableProperty(displayName = "onPulseReceived")
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
    public ArrayList<PObject> getTargets() {
        return _targets;
    }

    @InspectableProperty.SetterFor("Target")
    public void setTarget(PObject target) {
        if (_targets == null) _targets = new ArrayList<>();
        _targets.add(target);
    }



    public void sendPulse(Object message) throws InterruptedException {

       for (PObject target : _targets){
           if (target == null) return;

           RoutingComponent rc = target.getRoutingComponent();

           long distance = (long) getOwner().getBodyComponent().getPixelCenter().sub(target.getBodyComponent().getPixelCenter()).length();

           if (rc == null)
               throw new RuntimeException("You're trying to send a message to an object that cannot receive it because it doesn't have a routing component\n" +
                       "OBJ = " + target.toString());

           //start a new thread to send the pulse
              Executors.newSingleThreadExecutor().submit(() -> {
                try {
                     Thread.sleep(distance);
                     rc.receivePulse(message);
                } catch (InterruptedException e) {
                     e.printStackTrace();
                }
              });

       }
    }

    public void receivePulse(Object message) {

        try {
        ((PMetronome) _owner).getBlinkingLigth().blink();
        }catch (Exception ignored) {
        }


        Map<String, Object> scope = new HashMap<>();
        scope.put("message", message);
        scope.put("x", _owner.getBodyComponent().getPixelCenter().x);
        scope.put("y", _owner.getBodyComponent().getPixelCenter().y);

        MusicalNoteComponent musicalNoteComponent = _owner.getComponent(MusicalNoteComponent.class);
        if (musicalNoteComponent != null) {
            MusicalNote note = musicalNoteComponent.getNote();
            scope.put("note", note);
        }

        if (_pulseCallback != null) {

            if (_pulseCallback.isKFunction()){
                KFunction kFunction = _pulseCallback.getKFunction();
                kFunction.execute(scope);

            } else if ( _pulseCallback.isRunnable() ){
                _pulseCallback.getRunnable().run();

            }
        }

        try {
            sendPulse(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
