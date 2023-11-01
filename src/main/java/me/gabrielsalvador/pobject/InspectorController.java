package me.gabrielsalvador.pobject;

import controlP5.*;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.utils.Color;
import org.jbox2d.common.Vec2;
import processing.core.PVector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;


public class InspectorController extends Group implements PropertyChangeListener {

    private static final int IDENTATION = 10 ;
    private final int DEFAULT_HEIGHT = 30;

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
        label.setColorBackground(Color.rgbToInt(0, 244, 0));
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
        //random color
        int color = Color.random();
        container.setColorBackground(color);

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
            controllers[i].setGroup(container);
            controllers[i].getCaptionLabel().hide();
        }

        /* Add the group to the inspector */
        addChildVertically(container);
        //indent container
        float[] position = container.getPosition();
        container.setPosition(position[0] + IDENTATION, position[1]);

    }

    private Controller[] buildControllerForProperty(Group container, PObjectProperty property, Class<?> type) {
        System.out.println("building controller for " + property.getName() + " of type " + type.getName());
        if (type == boolean.class) {
            Toggle toggle = cp5.addToggle(property.getName())
                    .setPosition(0, 0)
                    .setSize(20, 20)
                    .setGroup(this)
                    .setValue((Boolean) property.getValue());

            toggle.getCaptionLabel().hide();

            setupCheckboxCallback(toggle, property);

            return new Controller[]{toggle};
        } else if (type == int.class) {
            Numberbox numberbox = cp5.addNumberbox(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 20)
                    .setGroup(this)
                    .setValue((Integer) property.getValue());

            setupNumberboxCallback(numberbox, property);

            return new Controller[]{numberbox};
        } else if (type == Float.class || type == float.class) {
            Numberbox numberbox = cp5.addNumberbox(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 20)
                    .setGroup(this)
                    .setValue((Float) property.getValue());

            setupNumberboxCallback(numberbox, property);

            return new Controller[]{numberbox};
        } else if (type == String.class) {
            Textfield textfield = cp5.addTextfield(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 20)
                    .setGroup(this)
                    .setValue((String) property.getValue());
            textfield.setAutoClear(false);
            textfield.getCaptionLabel().hide();
            setupTextfieldCallback(textfield, property);

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
        else if (type.equals(Vec2.class)){

            Vec2 vector = (Vec2) property.getValue();
            float[] values = new float[]{vector.x,vector.y};

            Controller<Numberbox>[] controllers = new Controller[values.length];
            for (int i = 0; i < values.length; i++) {
                Numberbox numberbox = cp5.addNumberbox(property.getName() + i)
                        .setPosition(0, 0)
                        .setSize((container.getWidth()/2) / values.length, 20)
                        .setValue(values[i]);
                numberbox.getCaptionLabel().hide();

                controllers[i] = numberbox;
            }

            setupVec2Callback(controllers,property);

            return controllers;
        }
        else if(Component.class.isAssignableFrom(type)){ // if the property is a reference to a component
            ScrollableList list = cp5.addScrollableList(property.getName())
                    .setPosition(0, 0)
                    .setSize(100, 100)
                    .setGroup(this)
                    .setBarHeight(20)
                    .setItemHeight(20)
                    .setType(ScrollableList.DROPDOWN)
                    .setBackgroundColor(255)
                    .close();

            Component component = (Component) property.getOwner();
            HashMap<Class<? extends Component>,Component> components = component.getOwner().getComponents();
            for (Class<?> key : components.keySet()) {

                int i = 0;
                if(type.isAssignableFrom(key)) {
                    list.addItem(components.get(key).getName(),components.get(key));
                    //if its the current value, select it
                    if(components.get(key) == property.getValue()){
                        list.setValue(i);
                    }
                }
            }


            setupScrollableListCallback(list,property);

            return new Controller[]{list};
        }



        Textlabel t = new Textlabel(cp5, "Property" + property.getName());
        //get class name
        String className = type.getName();
        System.out.println("class name " + className);

        t.setText(className);
        return new Controller[]{t};

    }

    private void setupCheckboxCallback(Toggle toggle, PObjectProperty property) {
        toggle.addCallback(new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
                    boolean value = toggle.getState();
                    property.setValue(value);
                }
            }
        });
    }

    private void setupTextfieldCallback(Textfield textfield, PObjectProperty property) {
        textfield.addCallback(new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
                    String value = textfield.getText();
                    property.setValue(value);
                }
            }
        });
    }

    private void setupScrollableListCallback(Controller controller, PObjectProperty property) {
        controller.addCallback(new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {

                //on press
                if (callbackEvent.getAction() == ControlP5.ACTION_PRESS) {
                    controller.bringToFront();
                }

                ScrollableList controller = (ScrollableList) callbackEvent.getController();

                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
                    int index = (int) controller.getValue();
                    Object value = controller.getItem(index).get("value");
                    System.out.println("property set to " + value);
                    property.setValue(value);
                }
            }
        });
    }


    private void setupNumberboxCallback(Numberbox numberbox, PObjectProperty property) {
        numberbox.addCallback(new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
                    int value = (int) numberbox.getValue();
                    property.setValue(value);
                }
            }
        });
    }

    private void setupVec2Callback(Controller<Numberbox>[] numberboxes,PObjectProperty property){
        //this function will setup the callback on numberbox to update the vector property
        for (int i = 0; i < numberboxes.length; i++) {
            Controller<Numberbox> numberbox = numberboxes[i];
            numberbox.addCallback(new CallbackListener() {
                @Override
                public void controlEvent(CallbackEvent callbackEvent) {
                    if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
                        Vec2 vec2 = new Vec2(numberboxes[0].getValue(),numberboxes[1].getValue());
                        property.setValue(vec2);
                    }
                }
            });
        }
    }

}
