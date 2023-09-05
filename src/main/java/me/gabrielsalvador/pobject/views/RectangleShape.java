package me.gabrielsalvador.pobject.views;

import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

import java.io.Serializable;

public class RectangleShape extends Shape implements Serializable {

    private Vec2 _size;

    public RectangleShape( Vec2 size) {
        _size = size;
    }
    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float x, float y) {
        return  mouseX > x && mouseX < x + _size.x && mouseY > y && mouseY < y + _size.y;
    }

    @Override
    public void display(PGraphics graphics, float x, float y) {
        graphics.rect(x, y, _size.x, _size.y);
    }

    @Override
    public float[] getBoundayBox() {
        return new float[]{_size.x, _size.y};
    }


}
