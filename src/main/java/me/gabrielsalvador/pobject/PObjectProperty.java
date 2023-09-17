package me.gabrielsalvador.pobject;

import me.gabrielsalvador.common.SerializableRunnable;

import java.io.Serializable;



public class PObjectProperty implements Serializable {
    private final PObject _owner;
    private final String name;
    private Object value;
    private final Class<?> type;
    private SerializableRunnable onChanged;  // Callback when value changes


    public PObjectProperty(PObject _owner,String name, Class<?> type) {
        this._owner = _owner;
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


    public void setOnChanged(SerializableRunnable onChanged) {
        this.onChanged = onChanged;
    }

    public PObject getOwner() {
        return _owner;
    }
}
