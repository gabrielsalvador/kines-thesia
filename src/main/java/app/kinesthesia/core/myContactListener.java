package app.kinesthesia.core;

import app.kinesthesia.gui.processing.components.PlayNoteOnCollision;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

public class myContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        PhysicsBodyComponent objA = (PhysicsBodyComponent) contact.getFixtureA().getBody().getUserData();
        PhysicsBodyComponent objB = (PhysicsBodyComponent) contact.getFixtureB().getBody().getUserData();
        objB.onBeginContact(objA);
        objA.onBeginContact(objB);

        if(objA == null || objB == null){
            return;
        }

        PObject pObjectA = objA.getOwner();
        PlayNoteOnCollision onCollisionA = pObjectA.getComponent(PlayNoteOnCollision.class);
        if(onCollisionA != null){
            onCollisionA.onCollision(contact);
        }

        PObject pObjectB = objB.getOwner();
        PlayNoteOnCollision onCollisionB = pObjectB.getComponent(PlayNoteOnCollision.class);
        if(onCollisionB != null){
            onCollisionB.onCollision(contact);
        }




    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

}
