package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.dynamics.contacts.Contact;
import processing.core.PGraphics;

public class OnCollision extends Component{

    private int interval;

    @PObject.InspectableProperty(displayName = "Trigger Midi Note")
    private int getInterval(){
        return interval;
    }
    @PObject.InspectableProperty.SetterFor("Trigger Midi Note")
    public void setInterval(int value){
        interval = value ;
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
        theOtherBody.getJBox2DBody().applyLinearImpulse(contact.getFixtureB().getBody().getLinearVelocity(),theOtherBody.getJBox2DBody().getPosition());




        int pitch = AppController.getInstance().getGlobalScale().getPitchFromInterval(interval);
        int velocity = (int) contact.getFixtureB().getBody().getLinearVelocity().length() ;
        velocity = Math.min(velocity,127);
        AppController.getInstance().sendMidi(pitch,velocity);
    }


}
