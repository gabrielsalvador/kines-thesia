package me.gabrielsalvador.pobject;


import java.util.*;

import me.gabrielsalvador.views.View;


@Properties({
        @Property(name = "position", type = float[].class),
        @Property(name = "size", type = float[].class),
})
public class PObject {


    private boolean _isSelected = false;
    private final Set<PObject> _children = new HashSet<PObject>();
    private final LinkedHashMap<String, PObjectProperty> _properties = new LinkedHashMap<String, PObjectProperty>();
    private View<PObject> _view;
    private PObjectController _myController;

    public PObject() {
        /* Walks up the class hierarchy to get all annotations concerning the properties */
        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Properties propertiesAnnotation = currentClass.getAnnotation(Properties.class);
            if (propertiesAnnotation != null) {
                for (Property propertyAnnotation : propertiesAnnotation.value()) {
                    String name = propertyAnnotation.name();
                    Class<?> type = propertyAnnotation.type();

                    PObjectProperty property = new PObjectProperty(name, type);
                    property.setValue(Defaults.getDefaultValue(type));

                    addProperty(property);
                }
            }
            currentClass = currentClass.getSuperclass();
        }
    }



    public PObject setPosition(float[] position) {
        PObjectProperty property = getProperty("position");
        property.setValue(position);
        return this;
    }

    public float[] getPosition() {
        return (float[])getProperty("position").getValue();
    }

    public PObject setSize(float[] size) {
        getProperty("size").setValue(size);
        return this;
    }

    public float[] getSize() {
        return (float[])getProperty("size").getValue();
    }


    public PObject setIsSelected(boolean selectedState){
        _isSelected = selectedState;
        return this;
    }

    public boolean getIsSelected(){
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