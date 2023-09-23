package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.PObject.InspectableProperty.SetterFor;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.pobject.views.View;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class  Component implements Serializable {

    transient private  ArrayList<PObjectProperty> cachedProperties = new ArrayList<>();
    protected PObject _owner;
    transient protected View<Component> _view;

    public Component setView(View<Component> view) {
        _view = view;
        return this;
    }

    public View<Component> getView() {
        return _view;
    }

    public Component(PObject owner) {
        _owner = owner;
    }

    public void update() {
        // Implementation here
    }

    public abstract void display() ;
    public String getName() {
        // Implementation here
        return "";
    }



    /* TODO: move this to a more general place so more classes can have editable properties (like PObject it self)*/
    public ArrayList<PObjectProperty> getProperties() {
        if (!cachedProperties.isEmpty()) {
            return cachedProperties;
        }

        // Map to hold fieldName -> setterMethod
        Map<String, Method> setterMethods = new HashMap<>();

        // Collect setter methods first
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SetterFor.class)) {
                SetterFor setterForAnnotation = method.getAnnotation(SetterFor.class);
                setterMethods.put(setterForAnnotation.value(), method);
            }
        }

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InspectableProperty.class)) {
                InspectableProperty propertyAnnotation = field.getAnnotation(InspectableProperty.class);
                String displayName = propertyAnnotation.displayName().isEmpty() ? field.getName() : propertyAnnotation.displayName();

                try {
                    field.setAccessible(true);
                    PObjectProperty property = new PObjectProperty(this, displayName, field.getType()).setValue(field.get(this));

                    // Link the setter method to the property using the SetterFor annotation value
                    if (setterMethods.containsKey(displayName)) {
                        property.setSetter(setterMethods.get(displayName));
                    }

                    cachedProperties.add(property);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return cachedProperties;
    }

    public PObjectProperty getProperty(String name) {
        for (PObjectProperty property : getProperties()) {
            if (property.getName().equals(name)) {
                return property;
            }
        }
        return null;
    }

    public PObject getOwner() {
        return _owner;
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        cachedProperties = new ArrayList<>();
    }

    @Serial private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }



}

