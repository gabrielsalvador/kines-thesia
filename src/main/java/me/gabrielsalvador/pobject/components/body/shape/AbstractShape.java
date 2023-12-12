package me.gabrielsalvador.pobject.components.body.shape;

import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

import java.io.Serializable;

public abstract class AbstractShape implements Serializable {
    public abstract boolean isMouseOver(int mouseX, int mouseY, float x, float y);

    public abstract void display(PGraphics graphics) ;

    public abstract float[] getBoundaries() ;

    public abstract Vec2 getCenter();

    public abstract Vec2 getPixelCenter();
}
