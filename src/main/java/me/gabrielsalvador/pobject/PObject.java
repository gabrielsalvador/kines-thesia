package me.gabrielsalvador.pobject;


import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;

import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.views.View;



public abstract class PObject implements Serializable {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD})
    public @interface InspectableProperty {
        String displayName() default "";
        String setter() default "";  // Name of the setter method

        @Retention(RetentionPolicy.RUNTIME)
        public @interface SetterFor {
            String value();
        }
    }

    private boolean _isSelected = false;
    private boolean _isHovered = false;
    private final Set<PObject> _children = new HashSet<PObject>();
    transient private final LinkedHashMap<String, PObjectProperty> _properties = new LinkedHashMap<String, PObjectProperty>();
    private final LinkedHashMap<Class<? extends Component>,Component> _components = new LinkedHashMap<Class<? extends Component>,Component>();
    transient protected View<PObject> _view;


    public PObject() {
        //default body for every PObject
        addComponent(BodyComponent.class,new HologramBody(this));
    }

    //use this method for things that need to be initialized after deserialization but don't forget the constructor also needs to call it
    protected abstract void initialize();



    public PObject setIsSelected(boolean selectedState) {
        _isSelected = selectedState;
        return this;
    }

    public boolean getIsSelected() {
        return _isSelected;
    }


    public Set<PObject> addChild(PObject pObject) {
        _children.add(pObject);
        return _children;
    }


    public Set<PObject> getChildren() {
        return _children;
    }


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


    public <T extends Component> T getComponent(Class<? extends Component> componentClass) {
        return (T) _components.get(componentClass);
    }

    public <T extends Component> PObject addComponent(Class<T> _class,T instance) {
        _components.put(_class,instance);
        return this;
    }


    public View<PObject> getView() {
        return _view;
    }

    public <T extends BodyComponent> T getBodyComponent() {
        return getComponent(BodyComponent.class);
    }

    public PObject setView(View<PObject> view) {
        _view = view;
        return this;
    }


    public void onPressed(int x, int y){

    }

    public abstract void onEnter(int x, int y);
    public abstract void onLeave(int x, int y);

    public boolean getIsHovered() {
        return _isHovered;
    }

    public void setIsHovered(boolean isHovered,int x, int y) {
        // if not changed do nothing
        // if changed : call onHover or onLeave
        if(_isHovered != isHovered){
            _isHovered = isHovered;
            if(_isHovered){
                onEnter(x,y);
            }else{
                onLeave(x,y);
            }
        }
    }


   //get components
    public <T extends Component> HashMap<Class<? extends Component>,Component> getComponents() {
        return _components;
    }

    public abstract void remove();
}