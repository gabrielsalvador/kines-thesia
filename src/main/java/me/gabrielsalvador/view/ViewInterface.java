package me.gabrielsalvador.view;

public interface ViewInterface<T> {
    public ViewInterface<T> addModel(T model);

    public T getModel();

    public void display();
}
