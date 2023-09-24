package me.gabrielsalvador.pobject.components.body;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.body.shape.AbstractShape;
import me.gabrielsalvador.pobject.components.body.shape.RectanglePShape;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class HologramBody extends BodyComponent implements Serializable {

    private AbstractShape _shape;
    private Vec2 _position;
    private final ArrayList<HologramBody> _children = new ArrayList<>();
    private HologramBody _parent;

    public HologramBody(PObject owner,Vec2 position, Vec2 size){
        super(owner);
        _position = position;
        _shape = new RectanglePShape( size);
    }

    public HologramBody(PObject owner) {
        super(owner);
        _position = new Vec2(0,0);
        _shape = new RectanglePShape(new Vec2(10,10));
        initialize();
    }
    public void initialize(){
        setView(new HologramBodyView(this));
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
    public AbstractShape getShape() {
        return _shape;
    }

    @Override
    public void setShape(AbstractShape shape) {
        _shape = shape;
    }


    @Override
    public void update() {

    }

    @Override
    public void display(PGraphics graphics) {

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

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize();
    }

}
