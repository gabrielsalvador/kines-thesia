package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.pobject.PObjectProperty;

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

public class Component {

    private ArrayList<PObjectProperty> cachedProperties = new ArrayList<>();

    public void update() {
        // Implementation here
    }

    public String getName() {
        // Implementation here
        return "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD})
    public @interface InspectableProperty {
        String displayName() default "";
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

                    PObjectProperty property = new PObjectProperty(displayName, field.getType())
                            .setValue(field.get(this));

                    // Update the original field when PObjectProperty changes
                    property.setOnChanged(() -> {
                        try {
                            System.out.println("Updating field " + field.getName() + " with value " + property.getValue());
                            field.set(this, property.getValue());  // Update the original field when PObjectProperty changes
                        } catch (IllegalAccessException e) {
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

