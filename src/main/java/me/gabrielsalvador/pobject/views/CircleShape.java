package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.utils.MathUtils;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class CircleShape extends Shape {

    Vec2 _position;
    float _radius;

    public CircleShape(Vec2 position, float radius) {
        _position = position;
        _radius = radius;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float x, float y) {
        // inside the circle
        float d = MathUtils.distance(mouseX, mouseY, _position.x, _position.y);
        return d < Math.abs(_radius);
    }

    @Override
    public void display(PGraphics graphics, float x, float y) {
        graphics.ellipse(x, y, _radius * 2, _radius * 2);
    }

    @Override
    public float[] getBoundayBox() {
        return null;
    }



}