package me.gabrielsalvador.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.gabrielsalvador.controllers.PObjectController;
import me.gabrielsalvador.ui.views.PObjectView;
import me.gabrielsalvador.utils.Vector;



public class PObject {
    
    private Vector _position;
    private Vector _size;
    private final Set<PObject> _children = new HashSet<PObject>();
    private final HashMap<String, PObjectProperty<?>> _properties = new HashMap<String, PObjectProperty<?>>();
    private PObjectView _view;
    private PObjectController _myController;

    public PObject() {
        setView(new PObjectView(this));
    }


    public PObject setPosition(Vector position) {
        this._position = position;
        return this;
    }


    public Vector getPosition() {
        return _position;
    }


    public PObject setSize(Vector size) {
        this._size = size;
        return this;
    }


    public Vector getSize() {
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

    public PObjectView getView() {
        return _view;
    }

    public PObject setView(PObjectView view) {
        _view = view;
        return this;
    }
}