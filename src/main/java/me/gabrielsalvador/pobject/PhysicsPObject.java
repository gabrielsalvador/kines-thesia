package me.gabrielsalvador.pobject;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.views.PhysicsPObjectView;
import me.gabrielsalvador.pobject.views.View;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.io.IOException;
import java.io.Serial;


public class PhysicsPObject extends PObject{
    transient private Body _body;
    private Vec2 bufferPosition = new Vec2(); // used for serialization
    private ContactListener _contactListener;

    public PhysicsPObject(){
        initialize();

    }

    /*TODO: add this to PPObject class, so its a standard for every child*/
    protected void initialize() {
        setView((View)new PhysicsPObjectView(this));
        _body = PhysicsManager.getInstance().createCircle(bufferPosition, Config.PHYSICS_NOTE_DEFAULT_SIZE);
    }


    @Override
    public void onEnter(int x, int y) {

    }

    @Override
    public void onLeave(int x, int y) {

    }


    public float[] getPosition() {
        Vec2 pos = _body.getPosition();
        return new float[]{pos.x,pos.y};
    }

    public PhysicsPObject setPosition(float[] position){
        bufferPosition = new Vec2(position[0],position[1]);
        _body.setTransform(new Vec2(position[0],position[1]),_body.getAngle());
        return this;
    }



    public void onCollision(ContactListener contactListener){
    }


    @Serial
    private void readObject(java.io.ObjectInputStream aInputStream) throws ClassNotFoundException, java.io.IOException {
        aInputStream.defaultReadObject();
        initialize();
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream aOutputStream) throws IOException {
        bufferPosition = _body.getPosition();
        aOutputStream.defaultWriteObject();
    }


}




