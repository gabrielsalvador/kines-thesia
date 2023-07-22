package me.gabrielsalvador.ui.views;

import processing.core.PGraphics;

public interface ViewInterface<T> {
    

    public T getModel();

    public void display(PGraphics graphics);
    public boolean isMouseOver(int mouseX, int mouseY);
}
