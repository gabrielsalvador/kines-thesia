package me.gabrielsalvador.pobject;

import controlP5.*;
import me.gabrielsalvador.core.AppController;
import processing.core.PVector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;


public class InspectorController extends Group implements PropertyChangeListener {

    private int DEFAULT_HEIGHT = 30;
    private PObject _object;

    public InspectorController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        AppController.getInstance().addPropertyChangeListener("selectedObjects", this);
    }
 
    @Override
    public void didSetupLayout() {

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.clear();
        if (evt.getNewValue() == null) return;
        if (!(evt.getNewValue() instanceof PObject selectedObject)) return;
        if (!evt.getPropertyName().equals("selectedObjects")) return;

        /* Get the properties from the object */
        HashMap<String, PObjectProperty> properties = selectedObject.getProperties();

        /* Iterate over the properties and build the controllers to change them*/
        for (String key : properties.keySet()) {
            PObjectProperty property = properties.get(key);
            Class<?> type = property.getType();

            buildGroupForProperty(property, type);
        }

    }

    private void buildGroupForProperty(PObjectProperty property, Class<?> type) {
        /* Build a Group for each property
         * 50% of the width for the label
         * 50% of the width for the controller
         * */

        Group container = new Group(cp5, "PropertyGroup" + property.getName());
        container.setWidth(getWidth());
        container.setHeight(DEFAULT_HEIGHT);
        container.hideBar();

        /* Build the label */
        Textlabel label = new Textlabel(cp5, "label" + property.getName());
        label.moveTo(container);
        label.setText(property.getName());
        label.setHeight(container.getHeight());


        /* Build the controller */
        Controller<?>[] controllers = buildControllerForProperty(container, property, type);
        /* puts all controllers in place and adds a listener for then values change*/
        for(int i= 0; i < controllers.length; i++) {
            controllers[i].setWidth(container.getWidth()/2/controllers.length);
            controllers[i].setPosition(container.getWidth()/2 + (container.getWidth()/2/controllers.length) * (i), 0);
            controllers[i].setHeight(container.getHeight());
            controllers[i].setGroup(container);
            controllers[i].addListener(new ControlListener() {
                public void controlEvent(ControlEvent theEvent) {
                    //if property is a vector
                    if (type.getName().equals("[F")) {
                        float[] vector = new float[controllers.length];
                        for (int i = 0; i < controllers.length; i++) {
                            vector[i] = controllers[i].getValue();
                        }
                        property.setValue(vector);
                    } else {
                        property.setValue(theEvent.getController().getValue());
                    }
                }
            });
        }



        /* Add the group to the inspector */
        addChildVertically(container);
        //container.moveTo(this);

    }

    private Controller[] buildControllerForProperty(Group container, PObjectProperty property, Class<?> type) {
        if (type == Boolean.class) {
            Toggle toggle = cp5.addToggle(property.getName())
                    .setPosition(0, 0)
                    .setSize(20, 20)
                    .setGroup(this)
                    .setValue((Boolean) property.getValue());
            return new Controller[]{toggle};
        } else if (type == Integer.class) {
            Numberbox numberbox = cp5.addNumberbox(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 20)
                    .setGroup(this)
                    .setValue((Integer) property.getValue());
            return new Controller[]{numberbox};
        } else if (type == Float.class) {
            Numberbox numberbox = cp5.addNumberbox(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 20)
                    .setGroup(this)
                    .setValue((Float) property.getValue());
            return new Controller[]{numberbox};
        } else if (type == String.class) {
            Textfield textfield = cp5.addTextfield(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 20)
                    .setGroup(this)
                    .setValue((String) property.getValue());
            return new Controller[]{textfield};
        } else if (type == PVector.class) {
            Numberbox numberbox = cp5.addNumberbox(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 20)
                    .setGroup(this)
                    .setValue((Float) property.getValue());
            return new Controller[]{numberbox};
        } else if (type.getName().equals("[F")) {
            float[] vector = (float[]) property.getValue();

            Controller<?>[] controllers = new Controller[vector.length];

            for (int i = 0; i < vector.length; i++) {
                Numberbox numberbox = cp5.addNumberbox(property.getName() + i)
                        .setPosition(0, 0)
                        .setSize((container.getWidth()/2) / vector.length, 20)
                        .setValue(vector[i]);
                numberbox.getCaptionLabel().hide();

                controllers[i] = numberbox;
            }
            return controllers;

        }
        Textlabel t = new Textlabel(cp5, "Property" + property.getName());
        String typeName = type.getName().substring(0, Math.min(type.toString().length(), 10));

        t.setText(typeName + " Not Implmtld");
        return new Controller[]{t};

    }
}
