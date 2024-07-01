package me.gabrielsalvador.pobject.components.body.shape;

import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class CirclePShape extends PShape{

    private final Vec2 _size;

    public CirclePShape(Vec2 size) {
        _size = size;
    }

    public CirclePShape(float width, float height) {
        _size = new Vec2(width, height);
    }


    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float centerX, float centerY) {
        float distance = (float) Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2));
        return distance <= _size.x / 2;
    }

    @Override
    public void display(PGraphics graphics) {

        graphics.pushStyle();
        graphics.ellipseMode(graphics.CENTER);
        float diameter = _size.x ;

        graphics.fill(34,46,57);
        graphics.ellipse(0, 0, diameter + 4, diameter + 4);


        graphics.fill(255);
        graphics.ellipse(0, 0, diameter , diameter);
        graphics.popStyle();
    }

    @Override
    public float[] getBoundaries() {
        //return the 4 boundaries of the rectangle
        float[] boundaries = new float[2];
        boundaries[0] = _size.x;
        boundaries[1] = _size.y;
        return boundaries;
    }

    @Override
    public Vec2 getCenter() {
        // the rectangle is drawn form -size/2 to size/2
        return new Vec2(0,0);
    }

    @Override
    public Vec2 getPixelCenter() {
        return getCenter();
    }


    public Vec2 getSize() {
        return _size;
    }
}
