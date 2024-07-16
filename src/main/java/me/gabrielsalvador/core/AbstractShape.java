package me.gabrielsalvador.core;

import org.jbox2d.common.Vec2;

import java.io.Serializable;

public abstract class AbstractShape implements Serializable {
    public abstract boolean isMouseOver(int mouseX, int mouseY, float x, float y);


    public abstract float[] getBoundaries() ;

    public float[] getPixelBoundaries() {
        float[] boundaries = getBoundaries();
        float[] pixels = new float[boundaries.length];
        //convert to pixels
        pixels[0] = boundaries[0] * PhysicsManager.getInstance().worldToPixelScale(boundaries[0]);
        pixels[1] = boundaries[1] * PhysicsManager.getInstance().worldToPixelScale(boundaries[1]);
        pixels[2] = boundaries[2] * PhysicsManager.getInstance().worldToPixelScale(boundaries[2]);
        pixels[3] = boundaries[3] * PhysicsManager.getInstance().worldToPixelScale(boundaries[3]);
        return pixels;
    }

    public abstract Vec2 getCenter();


}
