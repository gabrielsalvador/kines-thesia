package me.gabrielsalvador.pobject;

import controlP5.ControlP5;
import controlP5.ControllerInterface;
import controlP5.Group;
import me.gabrielsalvador.pobject.components.Component;

import javax.lang.model.type.NoType;
import java.io.Serializable;
import java.lang.reflect.Method;


public class PObjectProperty implements Serializable {
    private final Object _owner;
    private final String name;
    private Object _value;
    private final Class<?> type;

    private Method setter;
    private Method getter;
    private Class<?> controllerClass;


    public PObjectProperty(Object owner, String name, Class<?> type) {
        this._owner = owner;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }



    public Object getValue() {
        Object value = null;
        if(getter != null) {
            try {
                return getter.invoke(_owner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
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

    public PObjectProperty setGetter(Method method) {
        getter = method;
        return this;
    }

    public PObjectProperty setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
        return this;
    }

    public Class<?> getControllerClass() {
        return this.controllerClass;
    }

    public ControllerInterface instantiateController(ControlP5 cp5) {
        if(controllerClass == NoType.class) {
            throw new RuntimeException("Trying to instantiate a controller for a property that doesn't have a controller class" + name);
        }
        try {
            ControllerInterface controller = (ControllerInterface) controllerClass.getConstructor(Component.class,ControlP5.class, String.class).newInstance(this._owner,cp5, name);

            if (controller instanceof Group) {
                //set some default styles for the group
                ((Group) controller).hideBar();
            }

            return (ControllerInterface) controller;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
