package me.gabrielsalvador.pobject;

import controlP5.*;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.Component;
import processing.core.PVector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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
        if (!evt.getPropertyName().equals("selectedObjects")) return;
        if (!(evt.getNewValue() instanceof ArrayList selectedObjects)) return;
        if (selectedObjects.size() == 0) return;

        /* For now we only edit the first element of the array, later I will work on an averaging system or something like that.*/
        PObject selectedObject = (PObject) selectedObjects.get(0);

        /* Get the components from the object */
        HashMap<Class<? extends Component>, Component> components = selectedObject.getComponents();

        /* Iterate over the components and build the controllers to change them*/
        for (Class<?> key : components.keySet()) {
            Component component = components.get(key);


            buildGroupsForComponent(component);
        }

    }

    private void buildGroupsForComponent(Component component) {
        Textlabel label = new Textlabel(cp5, "label" + component.getClass().getName());
        label.setText("Component: " + component.getName());
        addChildVertically(label);


        ArrayList<PObjectProperty> properties = component.getProperties();
        for (PObjectProperty property : properties) {
            buildGroupForProperty(property, property.getType());
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

            System.out.println("will add listener to controller: " + controllers[i].getName());

            controllers[i].addCallback(new CallbackListener() {
                @Override
                public void controlEvent(CallbackEvent theEvent) {

                    //get the text
                    Controller<?> controller = theEvent.getController();
                    if(controller instanceof Textfield){
                        System.out.println(theEvent.getAction());
                        String text = ((Textfield) controller).getText();
                        System.out.println("Textfield: " + text);
                }}
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
