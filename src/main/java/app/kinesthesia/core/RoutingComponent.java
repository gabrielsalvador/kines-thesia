package app.kinesthesia.core;



import app.kinesthesia.kinescript.ast.KFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class RoutingComponent extends Component {

    private ArrayList<PObject> _targets = new ArrayList<>();
    private int _subdivisions = 1;
    private CallbackWrapper _pulseCallback;
    @PObject.InspectableProperty(displayName = "Delay")
    private int _delay = 0;


    public RoutingComponent(PObject owner) {
        super(owner);
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

    @PObject.InspectableProperty(displayName = "Delay")
    public int getDelay() {
        return _delay;
    }





    @PObject.InspectableProperty(displayName = "onPulseReceived")
    public CallbackWrapper getPulseCallback() {
        return _pulseCallback;
    }
    @PObject.InspectableProperty.SetterFor("onPulseReceived")
    public void setPulseCallback(CallbackWrapper callback) {
        _pulseCallback = callback;
    }




    @PObject.InspectableProperty(displayName = "Subdivisions")
    public int getSubdivisions() {
        return _subdivisions;
    }


    @PObject.InspectableProperty.SetterFor("Subdivisions")
    public void setSubdivisions(int subdivisions) {
        _subdivisions = subdivisions;
        System.out.println("Subdivisions set to " + subdivisions);
    }



    @PObject.InspectableProperty.SetterFor("Delay")
    public void setDelay(int delay) {
        _delay = delay;
        System.out.println("Delay set to " + delay);
    }


    @PObject.InspectableProperty(displayName = "Target")
    public ArrayList<PObject> getTargets() {
        return _targets;
    }

    @PObject.InspectableProperty.SetterFor("Target")
    public void setTarget(PObject target) {
        if (_targets == null) _targets = new ArrayList<>();
        _targets.add(target);
    }



    public void sendPulse(Object message) throws InterruptedException {

       for (PObject target : _targets){
           if (target == null) return;

           RoutingComponent rc = target.getRoutingComponent();

           long distance = (long) getOwner().getBodyComponent().getPosition().sub(target.getBodyComponent().getPosition()).length();

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

        }catch (Exception ignored) {
        }


        Map<String, Object> scope = new HashMap<>();
        scope.put("message", message);
        scope.put("x", _owner.getBodyComponent().getPosition().x);
        scope.put("y", _owner.getBodyComponent().getPosition().y);

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
