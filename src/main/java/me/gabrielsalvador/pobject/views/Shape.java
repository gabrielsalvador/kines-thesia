package me.gabrielsalvador.pobject.views;

import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public abstract class Shape {
    public abstract boolean isMouseOver(int mouseX, int mouseY, float x, float y);

    public abstract void display(PGraphics graphics, float x, float y) ;

    public abstract float[] getBoundayBox() ;

}
