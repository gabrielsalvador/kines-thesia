package me.gabrielsalvador.pobject.components.body.shape;

import me.gabrielsalvador.utils.Vector;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class RectanglePShape extends PShape{

    private final Vec2 _size;

    public RectanglePShape(Vec2 size) {
        _size = size;
    }


    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float x, float y) {
        //check if the mouse is over the rectangle
        return mouseX > x - _size.x / 2 && mouseX < x + _size.x / 2 && mouseY > y - _size.y / 2 && mouseY < y + _size.y / 2;
    }

    @Override
    public void display(PGraphics graphics) {
        graphics.rect(-_size.x / 2, -_size.y / 2, _size.x, _size.y);
    }

    @Override
    public float[] getBoundaries() {
        //return the 4 boundaries of the rectangle
        float[] boundaries = new float[2];
        boundaries[0] = _size.x;
        boundaries[1] = _size.y;
        return boundaries;
    }


}
