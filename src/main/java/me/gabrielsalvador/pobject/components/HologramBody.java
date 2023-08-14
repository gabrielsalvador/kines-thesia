package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.pobject.views.RectangleShape;
import me.gabrielsalvador.pobject.views.Shape;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

public class HologramBody implements BodyComponent{

    private Shape _shape;
    private Vec2 _position;

    public HologramBody(Vec2 position, Vec2 size){
        _shape = new RectangleShape( size);
    }

    public HologramBody() {
        _position = new Vec2(0,0);
        _shape = new RectangleShape(new Vec2(0,0));
    }


    @Override
    public Vec2 getPosition() {
        return _position;
    }

    @Override
    public BodyComponent setPosition(Vec2 position) {
        _position = position;
        return this;
    }


    @Override
    public Shape getShape() {
        return _shape;
    }

    @Override
    public void setShape(Shape shape) {
        _shape = shape;
    }


    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return "HologramBody";
    }

    @Override
    public ArrayList<PObjectProperty> getProperties() {
        //just a test
        PObjectProperty p = new PObjectProperty("test",int.class);
        ArrayList<PObjectProperty> list = new ArrayList<>();
        list.add(p);
        return list;
    }
}
