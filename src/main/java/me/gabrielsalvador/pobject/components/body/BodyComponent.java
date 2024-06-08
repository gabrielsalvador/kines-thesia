package me.gabrielsalvador.pobject.components.body;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.shape.AbstractShape;
import org.jbox2d.common.Vec2;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import processing.core.PVector;

public abstract class BodyComponent extends Component {




    @InspectableProperty(displayName = "Position")
    public abstract Vec2 getPosition();
    public Vec2 getPixelPosition(){
        Vec2 position = getPosition();
        return PhysicsManager.getInstance().coordWorldToPixels(position.x, position.y);
    }


    protected AbstractShape _shape;

    public BodyComponent(PObject owner){
        super(owner);
    }



    public AbstractShape getShape(){
        return _shape;
    }

    public void setShape(AbstractShape shape){
        _shape = shape;
    }



    public abstract BodyComponent setPixelPosition(Vec2 vec2);

    public void setPixelPosition(int x, int y) {
        setPixelPosition(new Vec2(x, y));
    }



   public abstract void moveByPixels(Vec2 amount);

    @InspectableProperty.SetterFor("Position")
    public abstract BodyComponent setPosition(Vec2 position);


    public abstract void setTransform(Vec2 position, float angle);

    public Vec2 getPixelCenter() {

        return getPixelPosition().add(_shape.getPixelCenter());

    }

    public abstract void setAngle(float angle);
    public abstract void rotateBodyAroundPivot(Vec2 pivot, float angle);

    public abstract void setPixelTransform(Vec2 bufferedMousePosition, float rotatingAngle);


    public abstract float getAngle() ;

    public abstract void setVelocity(Vec2 vec2) ;
}
