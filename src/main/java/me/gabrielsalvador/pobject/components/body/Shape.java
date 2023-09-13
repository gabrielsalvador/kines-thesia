package me.gabrielsalvador.pobject.components.body;

import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

import java.io.Serializable;

public abstract class Shape implements Serializable {
    public abstract boolean isMouseOver(int mouseX, int mouseY, float x, float y);

    public abstract void display(PGraphics graphics, float x, float y) ;

    public abstract float[] getBoundayBox() ;

}
