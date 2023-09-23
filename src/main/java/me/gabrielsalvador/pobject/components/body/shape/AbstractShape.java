package me.gabrielsalvador.pobject.components.body.shape;

import processing.core.PGraphics;

import java.io.Serializable;

public abstract class AbstractShape implements Serializable {
    public abstract boolean isMouseOver(int mouseX, int mouseY, float x, float y);

    public abstract void display(PGraphics graphics, float x, float y) ;

    public abstract float[] getBoundaries() ;

}
