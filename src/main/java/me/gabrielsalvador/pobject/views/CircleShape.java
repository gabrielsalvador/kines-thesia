package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.utils.MathUtils;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class CircleShape extends Shape {


    float _radius;

    public CircleShape(Vec2 position, float radius) {

        _radius = radius;
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY, float x, float y) {
        // inside the circle
        float d = MathUtils.distance(mouseX, mouseY, x, y);
        return d < Math.abs(_radius);
    }

    @Override
    public void display(PGraphics graphics, float x, float y) {
        Vec2 pixelCoords = PhysicsManager.getInstance().coordWorldToPixels(x, y);
        float pixelRadius = PhysicsManager.getInstance().worldToPixelScale(_radius);

        graphics.ellipse(pixelCoords.x, pixelCoords.y, pixelRadius * 2, pixelRadius * 2);
    }

    @Override
    public float[] getBoundayBox() {
        return null;
    }



}