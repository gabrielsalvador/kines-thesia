package me.gabrielsalvador.pobject.components.body.shape;

import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.utils.Vector;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class RectanglePShape extends PShape{

    private final Vec2 _size;

    public RectanglePShape(Vec2 size) {
        _size = size;
    }


    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float centerX, float centerY) {
        Boolean betweenX = mouseX >= centerX - _size.x / 2 && mouseX <= centerX + _size.x / 2;
        Boolean betweenY = mouseY >= centerY - _size.y / 2 && mouseY <= centerY + _size.y / 2;
        return betweenX && betweenY;
    }

    @Override
    public void display(PGraphics graphics) {

        graphics.rect(-_size.x / 2, -_size.y / 2,_size.x, _size.y);
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
