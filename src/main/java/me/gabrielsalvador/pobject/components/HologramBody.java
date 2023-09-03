package me.gabrielsalvador.pobject.components;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.views.RectangleShape;
import me.gabrielsalvador.pobject.views.Shape;
import org.jbox2d.common.Vec2;
import java.io.Serializable;
import java.util.ArrayList;

public class HologramBody extends BodyComponent implements Serializable {

    private Shape _shape;
    private Vec2 _position;
    private ArrayList<HologramBody> _children = new ArrayList<>();
    private HologramBody _parent;

    public HologramBody(PObject owner,Vec2 position, Vec2 size){
        super(owner);
        _position = position;
        _shape = new RectangleShape( size);
    }

    public HologramBody(PObject owner) {
        super(owner);
        _position = new Vec2(0,0);
        _shape = new RectangleShape(new Vec2(0,0));
    }


    @Override
    public Vec2 getPosition() {
        return _parent != null ? _parent.getPosition().add(_position) : _position;
    }

    public Vec2 getPixelPosition() {
        Vec2 worldCoords = getPosition();
        Vec2 pixels = PhysicsManager.getInstance().coordWorldToPixels(worldCoords.x, worldCoords.y);
        return pixels;
    }

    @Override
    public BodyComponent setPosition(Vec2 position) {
        _position = position;
        return this;
    }
    public BodyComponent setPixelPosition(Vec2 position) {
        _position = PhysicsManager.getInstance().coordPixelsToWorld(position.x, position.y);
        return this;
    }

    @Override
    public void moveByPixels(Vec2 amount) {

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


    public void setParent(HologramBody parent){
        _parent = parent;
    }
    public HologramBody createChild(Vec2 position, Vec2 size){
        HologramBody child = new HologramBody(_owner,position, size);
        child.setParent(this);
        _children.add(child);
        return child;
    }
    public void addChild(HologramBody child){
        child.setParent(this);
        _children.add(child);
    }


}
