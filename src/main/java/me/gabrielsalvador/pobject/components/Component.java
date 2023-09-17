package me.gabrielsalvador.pobject.components;


import controlP5.Pointer;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.PObjectProperty;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;



import me.gabrielsalvador.pobject.PObjectProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Component implements Serializable {

    private final ArrayList<PObjectProperty> cachedProperties = new ArrayList<>();
    protected PObject _owner;

    public Component() {
    }
    public Component(PObject owner) {
        _owner = owner;
    }

    public void update() {
        // Implementation here
    }

    public String getName() {
        // Implementation here
        return "";
    }



    public ArrayList<PObjectProperty> getProperties() {
        if (!cachedProperties.isEmpty()) {
            return cachedProperties;
        }

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InspectableProperty.class)) {
                InspectableProperty propertyAnnotation = field.getAnnotation(InspectableProperty.class);
                String displayName = propertyAnnotation.displayName().isEmpty() ? field.getName() : propertyAnnotation.displayName();

                try {
                    field.setAccessible(true);

                    PObjectProperty property = new PObjectProperty(_owner, displayName, field.getType())
                            .setValue(field.get(this));


                    // Update the original field when PObjectProperty changes
                    property.setOnChanged(() -> {
                        try {
                            Field currentField = this.getClass().getDeclaredField(property.getName() );
                            currentField.setAccessible(true);
                            currentField.set(this, property.getValue());
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });

                    cachedProperties.add(property);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return cachedProperties;
    }





}

