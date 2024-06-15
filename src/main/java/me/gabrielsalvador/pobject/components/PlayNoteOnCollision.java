package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.components.musicalnote.MusicalNoteComponent;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.utils.Interval;
import me.gabrielsalvador.utils.MusicalNote;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PGraphics;

public class PlayNoteOnCollision extends Component {



//
//    KFunction customCallback;
//    @PObject.InspectableProperty(displayName = "Custom Callback",controllerClass = CodeEditor.class)
//    public KFunction getCustomCallback(){
//        return customCallback;
//    }
//    @PObject.InspectableProperty.SetterFor("Custom Callback")


    public PlayNoteOnCollision(PObject owner) {
        super(owner);
    }

    @Override
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
        //get the other body
        PhysicsBodyComponent theOtherBody = (PhysicsBodyComponent) contact.getFixtureB().getBody().getUserData();
        //add velocity to the body
        float angle = (float) Math.random() * 360;
        Vec2 randomVec = new Vec2((float) Math.cos(angle), (float) Math.sin(angle));
        theOtherBody.getJBox2DBody().applyLinearImpulse(contact.getFixtureB().getBody().getLinearVelocity().add(randomVec), theOtherBody.getJBox2DBody().getPosition());

        PhysicsBodyComponent me = (PhysicsBodyComponent) contact.getFixtureA().getBody().getUserData();
        MusicalNoteComponent MNC = me.getOwner().getComponent(MusicalNoteComponent.class);
        Interval resonatorInterval = MNC.getInterval();

        int chord = MidiManager.getInstance().getChord();
        MusicalNote note = MidiManager.getInstance().getScale().doInterval(chord + resonatorInterval.interval);


        int velocity = (int) contact.getFixtureB().getBody().getLinearVelocity().length();
//        velocity = Math.min(velocity, 127);
//
        int channel = MNC.getMidiChannel();
        MidiManager.getInstance().scheduleNote(channel,note.getPitch(), velocity,100);

    }


}
