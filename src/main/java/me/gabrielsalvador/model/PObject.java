package me.gabrielsalvador.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import processing.core.PVector;

public class PObject {
    PVector position;
    PVector size;
    private final Set<PObject> _children = new HashSet<PObject>();
    private final HashMap<String, PObjectProperty<?>> _properties = new HashMap<String, PObjectProperty<?>>();

    public PObject() {
    }


    PObject setPosition(PVector position) {
        this.position = position;
        return this;
    }


    public PVector getPosition() {
        return position;
    }


    PObject setSize(PVector size) {
        this.size = size;
        return this;
    }


    public PVector getSize() {
        return size;
    }


    public Set<PObject> addChildren(PObject pObject) {
        _children.add(pObject);
        return _children;
    }



    
    public PObjectProperty<?> getProperty(String name) {
        return _properties.get(name);
    }
}