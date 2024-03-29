package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.components.controllers.CodeEditor;
import me.gabrielsalvador.pobject.components.musicalnote.MusicalNoteComponent;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.utils.Interval;
import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PGraphics;

import java.util.HashMap;
import java.util.Map;

public class OnCollision extends Component {


    public KFunction onCollisionFunction = null;
    public Map<String, Object> scope = new HashMap<>();

    @PObject.InspectableProperty(displayName = "On Collision Function", controllerClass = CodeEditor.class)
    public KFunction getOnCollisionFunction() {
        return onCollisionFunction;
    }
    @PObject.InspectableProperty.SetterFor("On Collision Function")
    public void setOnCollisionFunction(KFunction function) {
        onCollisionFunction = function;
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
        MusicalNoteComponent MNC = me.getOwner().getComponent(MusicalNoteComponent.class);
        Interval resonatorInterval = MNC.getInterval();

        int chord = MidiManager.getInstance().getChord();
        MusicalNote note = MidiManager.getInstance().getKey().doInterval(chord + resonatorInterval.interval);


        int velocity = (int) contact.getFixtureB().getBody().getLinearVelocity().length();
//        velocity = Math.min(velocity, 127);
//
//        int channel = MNC.getMidiChannel();
//        MidiManager.getInstance().scheduleNote(channel,note.getPitch(), velocity);
        scope.put("pitch", note.getPitch());
        scope.put("velocity", velocity);
        scope.put("channel", MNC.getMidiChannel());

        if(onCollisionFunction != null) onCollisionFunction.execute(scope);
    }


}
