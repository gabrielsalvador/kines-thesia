package me.gabrielsalvador.core;

import org.jbox2d.common.Vec2;


public abstract class BodyComponent extends Component {

    public AbstractShape _shape;

    @PObject.InspectableProperty(displayName = "Position")
    public abstract Vec2 getPosition();


    public BodyComponent(PObject owner){
        super(owner);
    }


    public AbstractShape getShape(){
        return _shape;
    }


    public void setShape(AbstractShape shape){_shape = shape;}

    @PObject.InspectableProperty.SetterFor("Position")

    public abstract BodyComponent setPosition(Vec2 position);

    public abstract void setTransform(Vec2 position, float angle);

    public abstract void setAngle(float angle);

    public abstract void rotateBodyAroundPivot(Vec2 pivot, float angle);

    public abstract float getAngle() ;

    public abstract void setVelocity(Vec2 vec2) ;

    public void setPosition(int i, int i1) {
        
    }
}
