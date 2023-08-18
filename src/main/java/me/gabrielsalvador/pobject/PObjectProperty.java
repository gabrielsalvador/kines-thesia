package me.gabrielsalvador.pobject;

import java.io.Serializable;

public class PObjectProperty implements Serializable {
    private String name;
    private Object value;
    private Class<?> type;
    private Runnable onChanged;  // Callback when value changes


    public PObjectProperty(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PObjectProperty setValue(Object value) {
        this.value = value;
        if (onChanged != null) onChanged.run();
        return this;
    }

    public Object getValue() {

        return value;
    }

    public Class<?> getType() {
        return type;
    }


    public void setOnChanged(Runnable onChanged) {
        this.onChanged = onChanged;
    }
}
