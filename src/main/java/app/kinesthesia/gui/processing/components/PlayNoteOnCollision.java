package app.kinesthesia.gui.processing.components;

import app.kinesthesia.core.*;
import app.kinesthesia.kinescript.lang.Kinescript;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PGraphics;

import java.util.HashMap;

public class PlayNoteOnCollision extends Component {



//
CallbackWrapper customCallback;
    @PObject.InspectableProperty(displayName = "What happens on collision")
    public CallbackWrapper getCustomCallback(){
        return customCallback;
    }
    @PObject.InspectableProperty.SetterFor("What happens on collision")
    public void setCustomCallback(CallbackWrapper value){
        customCallback = value;
    }


    public PlayNoteOnCollision(PObject owner) {
        super(owner);
        customCallback = new CallbackWrapper( Kinescript.compileFunction("midi(channel,note,velocity)"));
    }


    public void display(PGraphics graphics) {

    }

    @Override
    public String getName() {
        return "Play Note On Collision";
    }

    @Override
    public void dispose() {

    }

    @Override
    public void remove() {

    }

    public void onCollision(Contact contact) {

//        //get the other body
//        PhysicsBodyComponent theOtherBody = (PhysicsBodyComponent) contact.getFixtureB().getBody().getUserData();
//        //add velocity to the body
//        float angle = (float) Math.random() * 360;
//        Vec2 randomVec = new Vec2((float) Math.cos(angle), (float) Math.sin(angle));
//        theOtherBody.getJBox2DBody().applyLinearImpulse(contact.getFixtureB().getBody().getLinearVelocity().add(randomVec), theOtherBody.getJBox2DBody().getPosition());
//
        PhysicsBodyComponent me = (PhysicsBodyComponent) contact.getFixtureA().getBody().getUserData();
        MusicalNoteComponent MNC = me.getOwner().getComponent(MusicalNoteComponent.class);
        Interval resonatorInterval = MNC.getInterval();

//
        int chord = MidiManager.getInstance().getChord();
        MusicalNote note = MidiManager.getInstance().getScale().doInterval(chord + resonatorInterval.interval);
//
        int velocity = (int) contact.getFixtureB().getBody().getLinearVelocity().length();
        velocity = Math.min(velocity, 127);

        int channel = MNC.getMidiChannel();
//        MidiManager.getInstance().scheduleNote(channel,note.getPitch(), velocity,100);

        HashMap<String, Object> scope = new HashMap<>();
        scope.put("channel", channel);
        scope.put("note", note.getPitch());
        scope.put("velocity", velocity);
        customCallback.getKFunction().execute(scope);

    }


}
