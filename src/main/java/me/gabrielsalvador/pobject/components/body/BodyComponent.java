package me.gabrielsalvador.pobject.components.body;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.shape.AbstractShape;
import org.jbox2d.common.Vec2;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;

public abstract class BodyComponent extends Component {


    private AbstractShape _shape;

    public BodyComponent(PObject owner){
        super(owner);
    }

    @InspectableProperty(displayName = "Position")
    public abstract Vec2 getPosition();
    public Vec2 getPixelPosition(){
        Vec2 position = getPosition();
        return PhysicsManager.getInstance().coordWorldToPixels(position.x, position.y);
    }
//    public BodyComponent setPosition(Vec2 position){
//        _position = position;
//        return this;
//    }

    public AbstractShape getShape(){
        return _shape;
    }

    public void setShape(AbstractShape shape){
        _shape = shape;
    }



    public abstract  BodyComponent setPixelPosition(Vec2 vec2);

    public void setPixelPosition(int x, int y) {
        setPixelPosition(new Vec2(x, y));
    }



   public abstract void moveByPixels(Vec2 amount);

    @InspectableProperty.SetterFor("Position")
    public abstract BodyComponent setPosition(Vec2 position);


}
