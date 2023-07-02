package me.gabrielsalvador.model.PObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.gabrielsalvador.controllers.PObjectController;
import me.gabrielsalvador.ui.views.ViewInterface;




public class PObject {
    
    private float[] _position = new float[5];
    private float[] _size = new float[5];
    
    private final Set<PObject> _children = new HashSet<PObject>();
    private final HashMap<String, PObjectProperty<?>> _properties = new HashMap<String, PObjectProperty<?>>();
    private ViewInterface<PObject> _view;
    private PObjectController _myController;

    public PObject() {
        
    }


    public PObject setPosition(float[] position) {
        this._position = position;
        return this;
    }


    public float[] getPosition() {
        return _position;
    }


    public PObject setSize(float[] size) {
        this._size = size;
        return this;
    }


    public float[] getSize() {
        return _size;
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

    public PObjectController getController() {
        return _myController;
    }

    public ViewInterface<PObject> getView() {
        return _view;
    }

    public PObject setView(ViewInterface<PObject> view) {
        _view = view;
        return this;
    }



}