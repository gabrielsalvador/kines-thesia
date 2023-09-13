package me.gabrielsalvador.pobject.components.body.shape;

import me.gabrielsalvador.utils.Vector;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class RectanglePShape extends PShape{

    private Vec2 _size;

    public RectanglePShape(Vec2 size) {
        _size = size;
    }


    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float x, float y) {
        return false;
    }

    @Override
    public void display(PGraphics graphics, float x, float y) {
        graphics.rect(x, y, _size.x, _size.y);
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
