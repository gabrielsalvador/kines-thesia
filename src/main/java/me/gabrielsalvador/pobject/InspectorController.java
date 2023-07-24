package me.gabrielsalvador.pobject;

import controlP5.*;
import me.gabrielsalvador.core.AppController;
import processing.core.PVector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InspectorController extends Group implements PropertyChangeListener {

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

        List<ControllerInterface<?>> controllers = getControllers().getControllers();

        this.clear();

        if (evt.getPropertyName().equals("selectedObjects")) {
            PObject pObject = (PObject) evt.getNewValue();
            if (pObject == null) return;

            HashMap<String, PObjectProperty> properties = pObject.getProperties();

            //iterate over properties
            for (String key : properties.keySet()) {
                PObjectProperty property = properties.get(key);

                ControllerInterface<?> controller;

                String controllerKey = key;
                if (property.getType() == Boolean.class) {
                    controller = new Bang(cp5, controllerKey);
                } else if (property.getType() == Integer.class || property.getType() == Float.class) {
                    controller = new Slider(cp5, controllerKey);
                } else if (property.getType() == PVector.class) {
                    for (char axis : new char[]{'x', 'y', 'z'}) {
                        controller = new Numberbox(cp5, controllerKey + axis);
                        controller.setPosition(0, 0);
                        controller.setSize(getWidth(), 20);
                        ((Group)controller).setGroup(this);
                        addChildHorizontally(controller);
                    }
                    continue;
                } else if (property.getType() == String.class) {
                    controller = new Textfield(cp5, controllerKey);
                }else if(property.getType().getName().equals("[F")){
                    controller = new Numberbox(cp5, controllerKey);
                    float[] array = (float[]) property.getValue();
//                    controller.setValue(array[0]);
                } else {
                    controller = new Textlabel(cp5, controllerKey);
                    ((Textlabel) controller).setText("property not supported:" + key  + " " + property.getType().toString());

                }

                controller.setPosition(0, 0);
                controller.setSize(getWidth(), 20);
                controller.moveTo(this);
                ((Controller)controller).plugTo(property, controllerKey);
                addChildHorizontally(controller);
                ((Controller<?>) controller).getCaptionLabel().hide();
            }


        }
    }
}
