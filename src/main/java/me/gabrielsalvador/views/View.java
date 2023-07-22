package me.gabrielsalvador.views;

import processing.core.PGraphics;

public interface View<T> {
    

    public T getModel();

    public void display(PGraphics graphics);
    public boolean isMouseOver(int mouseX, int mouseY);
}
