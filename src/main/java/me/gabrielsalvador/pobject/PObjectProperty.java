package me.gabrielsalvador.pobject;

import me.gabrielsalvador.common.SerializableRunnable;
import me.gabrielsalvador.pobject.components.Component;

import java.io.Serializable;
import java.lang.reflect.Method;


public class PObjectProperty implements Serializable {
    private final Component _owner;
    private final String name;
    private Object _value;
    private final Class<?> type;

    private Method setter;


    public PObjectProperty(Component owner, String name, Class<?> type) {
        this._owner = owner;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }



    public Object getValue() {

        return _value;
    }

    public Class<?> getType() {
        return type;
    }




    public Object getOwner() {
        return _owner;
    }

    public void setSetter(Method method) {
        setter = method;
    }

    public PObjectProperty setValue(Object value) {

        _value = value;
        if(setter == null) {
            return this;
        }
        try {
            setter.invoke(_owner,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

}
