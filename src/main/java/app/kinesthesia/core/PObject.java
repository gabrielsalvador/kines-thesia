package app.kinesthesia.core;


import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;

public class PObject implements Serializable, HasPProperties {

    transient private boolean _isSelected = false;
    private boolean _isHovered = false;
    private final Set<PObject> _children = new HashSet<>();
    transient private ArrayList<PObjectProperty> _cachedProperties;
    private final LinkedHashMap<Class<? extends Component>, Component> _components = new LinkedHashMap<>();



    public PObject() {
        // Default body for every PObject
        addComponent(BodyComponent.class, new HologramBody(this));
    }

    // Use this method for things that need to be initialized after deserialization
    protected void initialize() {}

    public void remove() {
            Kinesthesia.getInstance().queueModification(() -> {
                Kinesthesia.getInstance().removePObjectImmediatly(this);
            });





    }
    // Setters and Getters
    public PObject setIsSelected(boolean selectedState) {
        _isSelected = selectedState;
        return this;
    }

    public boolean getIsSelected() {
        return _isSelected;
    }


    // Child Management
    public Set<PObject> addChild(PObject pObject) {
        _children.add(pObject);
        return _children;
    }

    public Set<PObject> getChildren() {
        return _children;
    }





    public ArrayList<PObjectProperty> getProperties() {

        if (_cachedProperties == null || _cachedProperties.isEmpty()) {
            List ownProperties = PObjectProperty.getProperties(this);
            List componentsProperties =  getComponents().values().stream()
                    .map(Component::getProperties)
                    .reduce(new ArrayList<PObjectProperty>(), (a, b) -> {
                        a.addAll(b);
                        return a;
                    });

            _cachedProperties = new ArrayList<>();
            _cachedProperties.addAll(ownProperties);
            _cachedProperties.addAll(componentsProperties);

        }
        return _cachedProperties;
    }

    // Component Management
    public <T extends Component> T getComponent(Class<? extends Component> componentClass) {
        if(_components == null) return null;
        return (T) _components.get(componentClass);
    }

    public <T extends Component> PObject addComponent(Class<T> _class, T instance) {
        //this needs to be 1:1 map
        //if there is already a component of this type, it will be replaced
        _components.remove(_class);
        _components.put(_class, instance);


        return this;
    }



    public <T extends BodyComponent> T getBodyComponent() {
        return getComponent(BodyComponent.class);
    }

    public <T extends Component> HashMap<Class<? extends Component>, Component> getComponents() {
        return _components;
    }

    // Event Handling
    public void onPressed(int x, int y) {

    }

    public void onEnter(int x, int y) {

    }

    public void onLeave(int x, int y) {

    }

    public void setIsHovered(boolean isHovered, int x, int y) {
        if (_isHovered != isHovered) {
            _isHovered = isHovered;
            if (_isHovered) {
                onEnter(x, y);
            } else {
                onLeave(x, y);
            }
        }
    }

    public boolean getIsHovered() {
        return _isHovered;
    }



    public RoutingComponent getRoutingComponent() {
        return getComponent(RoutingComponent.class);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD})
    public @interface InspectableProperty {
        String displayName() default "";

        @Retention(RetentionPolicy.RUNTIME)
        @interface SetterFor {
            String value();
        }


    }
}
