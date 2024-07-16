package me.gabrielsalvador.core;


import org.jbox2d.common.Vec2;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class HologramBody extends BodyComponent implements Serializable {


    private Vec2 _position;
    private final ArrayList<HologramBody> _children = new ArrayList<>();
    private HologramBody _parent;

    public HologramBody(PObject owner, Vec2 position, Vec2 size){
        this(owner);
        _position = position;
        _shape = new CirclePShape( size);
    }

    public HologramBody(PObject owner) {
        super(owner);
        _position = new Vec2(0,0);
        _shape = new CirclePShape(new Vec2(19,19));

        initialize();
    }
    public void initialize(){

    }


    @Override
    public Vec2 getPosition() {
        return _parent != null ? _parent.getPosition().add(_position) : _position;
    }

    public Vec2 getPixelPosition() {
        Vec2 worldCoords = getPosition();
        Vec2 pixels = MathUtils.coordWorldToPixels(worldCoords.x, worldCoords.y);
        return pixels;
    }



    public BodyComponent setPixelPosition(Vec2 position) {
        _position = MathUtils.coordPixelsToWorld(position.x, position.y);
        return this;
    }


    public void moveByPixels(Vec2 amount) {

    }

    @Override
    public BodyComponent setPosition(Vec2 position) {
        _position = position;
        return this;
    }

    @Override
    public void setTransform(Vec2 position, float angle) {
        _position = position;
    }


    @Override
    public void setAngle(float angle) {
        // cannot rotate hologram
    }

    @Override
    public void rotateBodyAroundPivot(Vec2 pivot, float angle) {
        // cannot rotate hologram
    }



    public void setPixelTransform(Vec2 bufferedMousePosition, float rotatingAngle) {
        // cannot rotate hologram
    }

    @Override
    public float getAngle() {
        return 0;
    }

    @Override
    public void setVelocity(Vec2 vec2) {
        // cannot move hologram
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
    public String getName() {
        return "HologramBody";
    }

    @Override
    public void dispose() {

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

    @Override
    public void remove() {
        // no cleanup needed
    }

}
