package me.gabrielsalvador.pobject;

import controlP5.ControlP5;
import controlP5.ControllerInterface;
import controlP5.Group;
import me.gabrielsalvador.pobject.components.Component;

import javax.lang.model.type.NoType;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PObjectProperty implements Serializable {
    private final Object _owner;
    private final String name;
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
                System.out.println("Error running getter for property " + name );
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

        if(setter == null) {
            return this;
        }
        try {
            setter.invoke(_owner,value);
        } catch (Exception e) {
            System.out.println("Error running setter for property " + name );
        }
        return this;
    }

    public PObjectProperty setGetter(Method method) {
        getter = method;
        return this;
    }

    @SuppressWarnings("all")
    public PObjectProperty setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
        return this;
    }

    public Class<?> getControllerClass() {
        return this.controllerClass;
    }





    /* Goes through all the fields of the component and creates a PObjectProperty if they are market with the InspectableProperties anotation */
    /* this is a static method because it is used by at least Component and PObject */
    public static ArrayList<PObjectProperty> getProperties(Object object) {
        // Map to hold fieldName -> setterMethod
        Map<String, Method> setterMethods = new HashMap<>();

        ArrayList<PObjectProperty> properties = new ArrayList<>();


        Class<?> currentClass = object.getClass();

        // Collect methods and fields from this class and all superclasses
        while (currentClass != null) {

            // Collect setter methods
            for (Method method : currentClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(PObject.InspectableProperty.SetterFor.class)) {
                    PObject.InspectableProperty.SetterFor setterForAnnotation = method.getAnnotation(PObject.InspectableProperty.SetterFor.class);
                    setterMethods.put(setterForAnnotation.value(), method);
                }
            }

            // Check fields for the InspectableProperty annotation
            for (Method method : currentClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(PObject.InspectableProperty.class)) {

                    PObject.InspectableProperty propertyAnnotation = method.getAnnotation(PObject.InspectableProperty.class);
                    String displayName = propertyAnnotation.displayName().isEmpty() ? method.getName() : propertyAnnotation.displayName();

                    Class<?> controllerClass = propertyAnnotation.controllerClass();

                    method.setAccessible(true);
                    PObjectProperty property = new PObjectProperty(object, displayName, method.getReturnType()).setGetter(method);
                    property.setControllerClass(controllerClass);

                    // Link the setter method to the property using the SetterFor annotation value
                    if (setterMethods.containsKey(displayName)) {
                        property.setSetter(setterMethods.get(displayName));
                    }

                    properties.add(property);
                }
            }

            // Move to the superclass for the next iteration
            currentClass = currentClass.getSuperclass();
        }
        return properties;
    }

    @Override
    public String toString() {
        return "[Property " + name + "]";
    }
}
