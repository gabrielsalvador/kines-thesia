package me.gabrielsalvador.ui.views;

import processing.core.PGraphics;

public interface ViewInterface<T> {
    public ViewInterface<T> addModel(T model);

    public T getModel();

    public void display(PGraphics graphics);
}
