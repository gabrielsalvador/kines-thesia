package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.pobject.PObjectProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;

public interface Component {


    public void update();

    public String getName();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD})
    public @interface InspectableProperty {
        String displayName() default "";
    }
    default ArrayList<PObjectProperty> getProperties() {
        ArrayList<PObjectProperty> properties = new ArrayList<>();

        // Extract fields annotated with InspectableProperty
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InspectableProperty.class)) {
                InspectableProperty propertyAnnotation = field.getAnnotation(InspectableProperty.class);
                String displayName = propertyAnnotation.displayName().isEmpty() ? field.getName() : propertyAnnotation.displayName();

                try {
                    field.setAccessible(true);
                    properties.add(new PObjectProperty(displayName, field.getType()).setValue(field.get(this)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // You can extend this to extract getters and setters annotated with InspectableProperty in a similar manner.

        return properties;
    }


}
