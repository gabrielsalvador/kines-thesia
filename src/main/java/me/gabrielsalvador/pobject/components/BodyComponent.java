package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.pobject.views.RectangleShape;
import me.gabrielsalvador.pobject.views.Shape;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;

public interface BodyComponent extends Component {

    public Vec2 getPosition();
    public BodyComponent setPosition(Vec2 position);


    Shape getShape();

    void setShape(Shape shape);
}
