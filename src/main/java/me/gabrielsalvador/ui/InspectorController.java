package me.gabrielsalvador.ui;

import controlP5.*;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.HasPProperties;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectProperty;

import javax.lang.model.type.NoType;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InspectorController extends Group implements PropertyChangeListener {

    private static final int IDENTATION = 10;
    private final int DEFAULT_HEIGHT = 30;

    public InspectorController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        AppController.getInstance().addPropertyChangeListener("selectedObjects", this);


    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!evt.getPropertyName().equals("selectedObjects")) return;

        this.clear(); // clear the controllers in the inspector

        ArrayList<HasPProperties> selectedObjects = evt.getNewValue() instanceof ArrayList ? (ArrayList<HasPProperties>) evt.getNewValue() : new ArrayList<>();

        if (selectedObjects == null || selectedObjects.isEmpty()) {
            //general settings
            selectedObjects = new ArrayList<>();
            selectedObjects.add(MidiManager.getInstance());

        }


        Map<Class<?>, ArrayList<PObjectProperty>> aggregatedProperties = aggregatePropertiesByClass(selectedObjects);

        //sort the properties by name so they are always in the same order
        aggregatedProperties.forEach((type, properties) -> properties.sort((p2,p1) -> p1.getName().compareTo(p2.getName())));


        aggregatedProperties.forEach(
                (type, properties) -> {
                    if (type != NoType.class) {
                        try {
                            CustomGroup editor = (CustomGroup) type.getConstructor(ControlP5.class, String.class, ArrayList.class).newInstance(cp5, type.getName(), properties);
                            PropertyEditor propertyEditor = (PropertyEditor) editor; // editor has to be a property editor

                            editor.resize(getWidth(), propertyEditor.getHeightForInspector());
                            editor.moveTo(this);
                            addChildVertically(editor);
                            addChildVertically(new Spacer(cp5, "spacer").setHeight(IDENTATION).setWidth(getWidth()).moveTo(this));

                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }


    //aggregates all properties by the class of their editor controller
    public Map<Class<?>, ArrayList<PObjectProperty>> aggregatePropertiesByClass(ArrayList<HasPProperties> selectedObjects) {
        Map<Class<?>, ArrayList<PObjectProperty>> properties = new HashMap<>();

        for (HasPProperties object : selectedObjects) {
            ArrayList<PObjectProperty> objectProperties = object.getProperties();
            for (PObjectProperty property : objectProperties) {
                Class<?> type = property.getControllerClass();
                if (!properties.containsKey(type)) {
                    properties.put((Class<? extends CustomGroup>) type, new ArrayList<>());
                }

                properties.get(type).add(property);
            }
        }

        return properties;

    }






}
