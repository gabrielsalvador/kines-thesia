package me.gabrielsalvador.pobject;

public class PObjectProperty {
    private String name;
    private Object value;
    private Class<?> type;

    public PObjectProperty(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PObjectProperty setValue(Object value) {
        this.value = value;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getType() {
        return type;
    }
}
