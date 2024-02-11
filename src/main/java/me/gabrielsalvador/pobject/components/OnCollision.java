package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.components.musicalnote.MusicalNoteComponent;
import me.gabrielsalvador.utils.Interval;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PGraphics;

public class OnCollision extends Component {


    @PObject.InspectableProperty(displayName = "Trigger Midi Note")

    @PObject.InspectableProperty.SetterFor("Trigger Midi Note")
    public void setNote(Interval note) {

    }


    public OnCollision(PObject owner) {
        super(owner);
    }

    @Override
    public void display(PGraphics graphics) {

    }

    @Override
    public String getName() {
        return "OnCollision";
    }

    @Override
    public void remove() {

    }

    public void onCollision(Contact contact) {
        //get the other body
        PhysicsBodyComponent theOtherBody = (PhysicsBodyComponent) contact.getFixtureB().getBody().getUserData();
        //add velocity to the body
        theOtherBody.getJBox2DBody().applyLinearImpulse(contact.getFixtureB().getBody().getLinearVelocity(), theOtherBody.getJBox2DBody().getPosition());

        PhysicsBodyComponent me = (PhysicsBodyComponent) contact.getFixtureA().getBody().getUserData();
        MusicalNoteComponent MTC = me.getOwner().getComponent(MusicalNoteComponent.class);

        Interval objectsNote = MTC.getInterval();
        Scale globalScale = MidiManager.getInstance().getScale();
        MusicalNote keyRoot = MidiManager.getInstance().getKey();
        Interval chordRootInterval = MidiManager.getInstance().getChordRoot();
        MusicalNote chordRoot = keyRoot.applyInterval(chordRootInterval, globalScale);

        int semitonesFromChordRootToNote = chordRoot.applyInterval(objectsNote, globalScale).getPitch();
        int semitonesFromKeyRootToChordRoot = keyRoot.applyInterval(chordRootInterval, globalScale).getPitch();
        int pitch = semitonesFromChordRootToNote + semitonesFromKeyRootToChordRoot;


        int velocity = (int) contact.getFixtureB().getBody().getLinearVelocity().length();
        velocity = Math.min(velocity, 127);
        MidiManager.getInstance().scheduleNote(pitch, velocity);
    }


}
