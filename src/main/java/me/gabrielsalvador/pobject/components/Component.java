package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.PObject.InspectableProperty.SetterFor;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.pobject.views.View;
import processing.core.PGraphics;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    public abstract void display(PGraphics graphics) ;
    public abstract String getName();



    /* Goes through all the fields of the component and creates a PObjectProperty if they are market with the InspectableProperties anotation */
    public ArrayList<PObjectProperty> getProperties() {
        if (!cachedProperties.isEmpty()) {
            return cachedProperties;
        }

        // Map to hold fieldName -> setterMethod
        Map<String, Method> setterMethods = new HashMap<>();

        Class<?> currentClass = this.getClass();

        // Collect methods and fields from this class and all superclasses
        while (currentClass != null) {

            // Collect setter methods
            for (Method method : currentClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(SetterFor.class)) {
                    SetterFor setterForAnnotation = method.getAnnotation(SetterFor.class);
                    setterMethods.put(setterForAnnotation.value(), method);
                }
            }

            // Check fields for the InspectableProperty annotation
            for (Method method : currentClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(InspectableProperty.class)) {
                    InspectableProperty propertyAnnotation = method.getAnnotation(InspectableProperty.class);
                    String displayName = propertyAnnotation.displayName().isEmpty() ? method.getName() : propertyAnnotation.displayName();

                    method.setAccessible(true);
                    PObjectProperty property = new PObjectProperty(this, displayName, method.getReturnType()).setGetter(method);

                    // Link the setter method to the property using the SetterFor annotation value
                    if (setterMethods.containsKey(displayName)) {
                        property.setSetter(setterMethods.get(displayName));
                    }

                    cachedProperties.add(property);
                }
            }

            // Move to the superclass for the next iteration
            currentClass = currentClass.getSuperclass();
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

