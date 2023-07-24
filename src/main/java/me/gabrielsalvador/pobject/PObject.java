package me.gabrielsalvador.pobject;

import java.lang.reflect.Field;
import java.util.*;

import me.gabrielsalvador.views.View;




public class PObject {

    @Property(name = "position", type = float[].class)
    private float[] _position = new float[5];
    @Property(name = "size", type = float[].class)
    private float[] _size = new float[5];
    private boolean _isSelected = false;
    private final Set<PObject> _children = new HashSet<PObject>();
    private final LinkedHashMap<String, PObjectProperty> _properties = new LinkedHashMap<String, PObjectProperty>();
    private View<PObject> _view;
    private PObjectController _myController;

    public PObject() {
        /* Gets all the Properties from the class fields*/
        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                Property propertyAnnotation = field.getAnnotation(Property.class);

                if (propertyAnnotation != null) {
                    String name = propertyAnnotation.name();
                    Class<?> type = propertyAnnotation.type();

                    PObjectProperty property = new PObjectProperty(name, type);
                    addProperty(property);
                }
            }
            currentClass = currentClass.getSuperclass();
        }
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

    public PObject setIsSelected(boolean selectedState){
        _isSelected = selectedState;
        return this;
    }

    public boolean getIsSelected(){
        return _isSelected;
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

    
    public PObjectProperty getProperty(String name) {
        return _properties.get(name);
    }


    public PObject addProperty(PObjectProperty property) {
        _properties.put(property.getName(), property);
        return this;
    }
    public HashMap<String, PObjectProperty> getProperties () {
        return _properties;
    }

    public PObject addProperties(Map<String,PObjectProperty> properties) {
        //add properties to the object
        for (String key : properties.keySet()) {
            addProperty(properties.get(key));
        }
        return this;
    }
    public PObjectController getController() {
        return _myController;
    }

    public View<PObject> getView() {
        return _view;
    }

    public PObject setView(View<PObject> view) {
        _view = view;
        return this;
    }



}