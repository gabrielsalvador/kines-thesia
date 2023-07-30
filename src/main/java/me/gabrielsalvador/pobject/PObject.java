package me.gabrielsalvador.pobject;


import java.io.*;
import java.util.*;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.routing.PatchSocket;
import me.gabrielsalvador.pobject.routing.annotations.InletAnnotation;
import me.gabrielsalvador.pobject.routing.annotations.InletsAnnotation;
import me.gabrielsalvador.views.View;


@Properties({
        @Property(name = "position", type = float[].class),
})

public class PObject implements Serializable  {


    private boolean _isSelected = false;
    private final Set<PObject> _children = new HashSet<PObject>();
    private final LinkedHashMap<String, PObjectProperty> _properties = new LinkedHashMap<String, PObjectProperty>();
    private final LinkedHashMap<String,PObject> _subObjects = new LinkedHashMap<String,PObject>();
    transient private View<PObject> _view;
    transient private boolean _isHovered = false;


    public PObject() {
        /* Walks up the class hierarchy to get all annotations concerning the properties and inlets */
        Class<?> currentClass = this.getClass();
        while (currentClass != null) {
            Properties propertiesAnnotation = currentClass.getAnnotation(Properties.class);
            if (propertiesAnnotation != null) {
                // Handling properties annotation
                for (Property propertyAnnotation : propertiesAnnotation.value()) {
                    String name = propertyAnnotation.name();
                    Class<?> type = propertyAnnotation.type();

                    PObjectProperty property = new PObjectProperty(name, type);
                    property.setValue(Defaults.getDefaultValue(type));

                    addProperty(property);
                }
            }

            InletsAnnotation inletsAnnotation = currentClass.getAnnotation(InletsAnnotation.class);
            if (inletsAnnotation != null) {

                for (InletAnnotation inletAnnotation : inletsAnnotation.value()) {
                    String name = inletAnnotation.name();
                    Class<?> type = inletAnnotation.type();


                    PatchSocket patchSocket = new PatchSocket(this);
                    AppController.getInstance().addPObject(patchSocket);
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


    public LinkedHashMap<String,PObject> getSubObjects() {
        return _subObjects;
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


    public View<PObject> getView() {
        return _view;
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

    public void onHover(int x, int y){}
    public void onLeave(int x, int y){}

    public boolean getIsHovered() {
        return _isHovered;
    }

    public void setIsHovered(boolean isHovered,int x, int y) {
        if(isHovered){
            onHover(x,y);
        }else {
            _isHovered = false;
            onLeave(x,y);
        }
    }
}