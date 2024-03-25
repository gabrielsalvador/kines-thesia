package me.gabrielsalvador.pobject.components;



import me.gabrielsalvador.pobject.InspectorController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObject.InspectableProperty;
import me.gabrielsalvador.pobject.PObject.InspectableProperty.SetterFor;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.pobject.views.View;
import processing.core.PGraphics;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class  Component implements Serializable {

    transient private  ArrayList<PObjectProperty> cachedProperties = new ArrayList<>();
    protected PObject _owner;
    protected Class<? extends View> _viewClass; //used to save the view class name on serialization and recreate the view on deserialization
    transient protected View<Component> _view;



    public View<Component> getView() {
        return _view;
    }


    public Component setView(View<Component> view) {
        _view = view;
        _viewClass = view.getClass();
        return this;
    }


    public Component(PObject owner) {
        _owner = owner;
    }

    public void update() {
        // Implementation here
    }

    public abstract void display(PGraphics graphics) ;
    public abstract String getName();




    public ArrayList<PObjectProperty> getProperties() {
        if (!cachedProperties.isEmpty()) {
            return cachedProperties;
        }
        else {
            cachedProperties = PObjectProperty.getProperties(this);
        }



        return cachedProperties;
    }


    public PObjectProperty getProperty(String name) {
        for (PObjectProperty property : getProperties()) {
            if (property.getName().equals(name)) {
                return property;
            }
        }
        return null;
    }

    public PObject getOwner() {
        return _owner;
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        in.defaultReadObject();
        cachedProperties = new ArrayList<>();
        if(_viewClass != null){
            //iterate over all constructors of the view class and try to find one that takes a Component as parameter
            for (Constructor<?> ctor : _viewClass.getConstructors()) {
                Class<?>[] paramTypes = ctor.getParameterTypes();
                if (paramTypes.length == 1 && paramTypes[0].isAssignableFrom(this.getClass())) {
                    setView((View<Component>) ctor.newInstance(this));
                    break;
                }
            }

        }
    }

    @Serial private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }


    public abstract void remove();



}

