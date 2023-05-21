package me.gabrielsalvador.model;

public class PObjectProperty<T>{
    String name;
    Object value;
    Class<T> valueType;

    public PObjectProperty(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }


    public PObjectProperty<T> setValue(T value) {
        this.value = value;
        return this;
    }


    @SuppressWarnings("unchecked")
    public T getValue() {
        return (T) value; 
    }

    
    public Class<?> getValueType() {
        return value.getClass();
    }
}