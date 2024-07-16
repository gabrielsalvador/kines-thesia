package me.gabrielsalvador.gui.processing.components.body.shape;


import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.core.PShape;

public class RectanglePShape extends PShape {

    private final Vec2 _size;

    public RectanglePShape(Vec2 size) {
        _size = size;
    }

    public RectanglePShape(float width, float height) {
        _size = new Vec2(width, height);
    }


    

    public Vec2 getSize() {
        return _size;
    }
}
