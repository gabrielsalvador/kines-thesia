package me.gabrielsalvador.view;

public interface View<T> {
    public View<T> addModel(T model);

    public T getModel();

    public void display();
}
