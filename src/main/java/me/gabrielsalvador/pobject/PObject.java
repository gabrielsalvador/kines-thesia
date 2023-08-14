package me.gabrielsalvador.pobject;


import java.io.*;
import java.util.*;

import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.HologramBody;
import me.gabrielsalvador.pobject.views.View;



public abstract class PObject implements Serializable {
    private boolean _isSelected = false;
    private boolean _isHovered = false;
    private final Set<PObject> _children = new HashSet<PObject>();
    private final LinkedHashMap<String, PObjectProperty> _properties = new LinkedHashMap<String, PObjectProperty>();
    private final LinkedHashMap<Class<? extends Component>,Component> _components = new LinkedHashMap<Class<? extends Component>,Component>();
    transient protected View<PObject> _view;


    public PObject() {
        //default body for every PObject
        addComponent(BodyComponent.class,new HologramBody());
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

    public PObject clone() {
        try {
            // Serialize the current object to a byte array
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(this);

            // Deserialize the byte array to create a new instance (clone) of the object
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
            return (PObject) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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


}