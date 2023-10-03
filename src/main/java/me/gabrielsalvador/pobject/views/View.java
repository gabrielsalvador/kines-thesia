package me.gabrielsalvador.pobject.views;

import processing.core.PGraphics;

public interface View<T> {
    

    T getModel();

    void display(PGraphics graphics, T model);
    boolean isMouseOver(int mouseX, int mouseY);


}
