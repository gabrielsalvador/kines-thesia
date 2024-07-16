package app.kinesthesia.core;



import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Component implements Serializable {

    transient private  ArrayList<PObjectProperty> cachedProperties = new ArrayList<>();
    protected PObject _owner;



    public Component(PObject owner) {
        _owner = owner;
    }

    public void update() {
        // Implementation here
    }


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

    public abstract void dispose();

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        in.defaultReadObject();
        cachedProperties = new ArrayList<>();

    }

    @Serial private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }


    public abstract void remove();



}

