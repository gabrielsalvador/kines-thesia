package me.gabrielsalvador.pobject;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.views.View;
import processing.core.PGraphics;

public class PObject implements Serializable {

    private boolean _isSelected = false;
    private boolean _isHovered = false;
    private final Set<PObject> _children = new HashSet<>();
    transient private final LinkedHashMap<String, PObjectProperty> _properties = new LinkedHashMap<>();
    private final LinkedHashMap<Class<? extends Component>, Component> _components = new LinkedHashMap<>();


    public PObject() {
        // Default body for every PObject
        addComponent(BodyComponent.class, new HologramBody(this));
    }

    // Use this method for things that need to be initialized after deserialization
    protected void initialize() {

    }

    public void remove() {
        for (PObject child : _children) {
            child.remove();
        }
        _children.clear();
        _properties.clear();
        _components.clear();
        AppController.getInstance().enqueueRemovePObject(this);

    }
    // Setters and Getters
    public PObject setIsSelected(boolean selectedState) {
        _isSelected = selectedState;
        return this;
    }

    public boolean getIsSelected() {
        return _isSelected;
    }


    // Child Management
    public Set<PObject> addChild(PObject pObject) {
        _children.add(pObject);
        return _children;
    }

    public Set<PObject> getChildren() {
        return _children;
    }

    // Property Management
    public PObjectProperty getProperty(String name) {
        return _properties.get(name);
    }

    public PObject addProperty(PObjectProperty property) {
        _properties.put(property.getName(), property);
        return this;
    }

    public HashMap<String, PObjectProperty> getProperties() {
        return _properties;
    }

    // Component Management
    public <T extends Component> T getComponent(Class<? extends Component> componentClass) {
        return (T) _components.get(componentClass);
    }

    public <T extends Component> PObject addComponent(Class<T> _class, T instance) {
        _components.put(_class, instance);
        return this;
    }



    public <T extends BodyComponent> T getBodyComponent() {
        return getComponent(BodyComponent.class);
    }

    public <T extends Component> HashMap<Class<? extends Component>, Component> getComponents() {
        return _components;
    }

    // Event Handling
    public void onPressed(int x, int y) {

    }

    public void onEnter(int x, int y) {

    }

    public void onLeave(int x, int y) {

    }

    public void setIsHovered(boolean isHovered, int x, int y) {
        if (_isHovered != isHovered) {
            _isHovered = isHovered;
            if (_isHovered) {
                onEnter(x, y);
            } else {
                onLeave(x, y);
            }
        }
    }

    public boolean getIsHovered() {
        return _isHovered;
    }

    public void display(PGraphics graphics) {
        for (Component component : _components.values()) {
            component.display(graphics);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD})
    public @interface InspectableProperty {
        String displayName() default "";
        String setter() default "";

        @Retention(RetentionPolicy.RUNTIME)
        @interface SetterFor {
            String value();
        }
    }
}
