package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.views.RectangleShape;
import me.gabrielsalvador.pobject.views.Shape;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;

public abstract class BodyComponent extends Component {

    protected Vec2 _position;
    private Shape _shape;

    public BodyComponent(){
        super();
    }
    public BodyComponent(PObject owner){
        super(owner);
    }
    public abstract Vec2 getPosition();
    public Vec2 getPixelPosition(){
        Vec2 position = getPosition();
        return PhysicsManager.getInstance().coordWorldToPixels(position.x, position.y);
    }
    public BodyComponent setPosition(Vec2 position){
        _position = position;
        return this;
    }

    public Shape getShape(){
        return _shape;
    }

    public void setShape(Shape shape){
        _shape = shape;
    }


    public BodyComponent setPixelPosition(Vec2 vec2) {
        _position = PhysicsManager.getInstance().coordPixelsToWorld(vec2.x, vec2.y);
        return this;
    }



   public abstract void moveByPixels(Vec2 amount);
}
