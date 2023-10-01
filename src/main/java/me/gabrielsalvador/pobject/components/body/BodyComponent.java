package me.gabrielsalvador.pobject.components.body;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.shape.AbstractShape;
import org.jbox2d.common.Vec2;

public abstract class BodyComponent extends Component {

    protected Vec2 _position;
    private AbstractShape _shape;


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

    public AbstractShape getShape(){
        return _shape;
    }

    public void setShape(AbstractShape shape){
        _shape = shape;
    }



    public BodyComponent setPixelPosition(Vec2 vec2) {
        _position = PhysicsManager.getInstance().coordPixelsToWorld(vec2.x, vec2.y);
        return this;
    }
    public void setPixelPosition(int x, int y) {
        setPixelPosition(new Vec2(x, y));
    }



   public abstract void moveByPixels(Vec2 amount);


}
