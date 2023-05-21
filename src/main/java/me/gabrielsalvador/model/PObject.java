package me.gabrielsalvador.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.gabrielsalvador.utils.Vector;



public class PObject {
    
    Vector position;
    Vector size;
    private final Set<PObject> _children = new HashSet<PObject>();
    private final HashMap<String, PObjectProperty<?>> _properties = new HashMap<String, PObjectProperty<?>>();



    public PObject() {
    }


    public PObject setPosition(Vector position) {
        this.position = position;
        return this;
    }


    public Vector getPosition() {
        return position;
    }


    public PObject setSize(Vector size) {
        this.size = size;
        return this;
    }


    public Vector getSize() {
        return size;
    }


    public Set<PObject> addChild(PObject pObject) {
        _children.add(pObject);
        return _children;
    }
 

    public Set<PObject> getChildren() {
        return _children;
    }

    
    public PObjectProperty<?> getProperty(String name) {
        return _properties.get(name);
    }
    public PObject addProperty(PObjectProperty<?> property) {
        _properties.put(property.getName(), property);
        return this;
    }
}