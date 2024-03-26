package me.gabrielsalvador.pobject;

import controlP5.*;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.controllers.CodeEditor;
import me.gabrielsalvador.pobject.components.controllers.KKnob;

import javax.lang.model.type.NoType;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
        if (!evt.getPropertyName().equals("selectedObjects")) return;

        this.clear(); // clear the controllers in the inspector

        ArrayList<PObject> changedSelection = evt.getNewValue() instanceof ArrayList ? (ArrayList<PObject>) evt.getNewValue() : new ArrayList<>();

        if (changedSelection == null || changedSelection.isEmpty() ){
//            buildGeneralSettings();
            return;
        }



        Map<Class<?>,ArrayList<PObjectProperty>> propertiesOfController = new HashMap<>();

        //go through all the selected objects and separate the properties by controller
        for (PObject object : changedSelection) {

            //for PObject properties
            ArrayList<PObjectProperty> mainProperties = object.getProperties();
            for (PObjectProperty property : mainProperties) {
                if(propertiesOfController.containsKey(property.getControllerClass())){

                    propertiesOfController.get(property.getControllerClass()).add(property);
                }else{
                    ArrayList<PObjectProperty> newProperties = new ArrayList<>();
                    newProperties.add(property);
                    propertiesOfController.put(property.getControllerClass(),newProperties);
                }
            }

            // for Component properties
            for (Component component : object.getComponents().values()) {
                ArrayList<PObjectProperty> componentProperties = component.getProperties();
                for (PObjectProperty property : componentProperties) {
                    if(propertiesOfController.containsKey(property.getControllerClass())){

                        propertiesOfController.get(property.getControllerClass()).add(property);
                    }else{
                        ArrayList<PObjectProperty> newProperties = new ArrayList<>();
                        newProperties.add(property);
                        propertiesOfController.put(property.getControllerClass(),newProperties);
                    }
                }
            }
        }

        for ( Class<?> controllerType : propertiesOfController.keySet()) {

            if(controllerType == NoType.class) continue;
            ArrayList<PObjectProperty> properties = propertiesOfController.get(controllerType);

            ControllerInterface<?> controller = properties.get(0).instantiateController(cp5);

            if (controller != null){
                if(controller instanceof Group){
                    ((Group) controller).setWidth(getWidth());
                    if (controller instanceof KKnob){
                        ((KKnob) controller).setSize(30,30);
                    }
                }
                addChildVertically(controller);
                addChildVertically(new Spacer(cp5, "spacer" + controller.getName()).setSize(getWidth(), 10));
            }

        }



    }

//    private void buildGroupsForComponent(Component component) {
//        //title for the component
//        Group componentGroup = new Group(cp5, "ComponentGroup" + component.getName());
//        componentGroup.setWidth(getWidth());
//        componentGroup.setBackgroundColor(Color.random());
//        componentGroup.hideBar();
//        Textlabel label = new Textlabel(cp5, "label" + component.getClass().getName());
//        label.setText("Component " + component.getName());
//        label.setGroup(componentGroup);
//        componentGroup.setSize(getWidth(), label.getHeight());
//
//        addChildVertically(componentGroup);
//
//
//        addChildVertically(new Spacer(cp5, "spacer" + component.getName()).setSize(getWidth(), 10)); //make is prettier
//
//
//        ArrayList<PObjectProperty> properties = component.getProperties();
//        for (PObjectProperty property : properties) {
//             buildGroupForProperty(property, property.getType());
//        }
//
//        addChildVertically(new Spacer(cp5, "spacer" + component.getName()).setSize(getWidth(), 30)); //make is prettier
//    }
//
//
//
//    /* Build a Group for each property
//     * 50% of the width for the label
//     * 50% of the width for the controller
//     * */
//
//    private void buildGroupForProperty(PObjectProperty property, Class<?> type) {
//
//        Group container = new Group(cp5, "PropertyGroup" + property.getName());
//        container.setWidth(getWidth());
//        container.setHeight(DEFAULT_HEIGHT);
//        container.hideBar();
//        //random color
//        int color = Color.random();
//        container.setColorBackground(color);
//
//        /* Build the label */
//        Textlabel label = new Textlabel(cp5, "label" + property.getName());
//        label.moveTo(container);
//        label.setText(property.getName());
//        label.setHeight(container.getHeight());
//
//        /* Build the controller */
//        Controller<?>[] controllers = buildControllerForProperty(container, property, type);
//        /* puts all controllers in place and adds a listener for then values change*/
//        for(int i= 0; i < controllers.length; i++) {
//            controllers[i].setWidth(container.getWidth()/2/controllers.length);
//            controllers[i].setPosition((float) container.getWidth() /2 + ((float) container.getWidth() /2/controllers.length) * (i), 10);
//            controllers[i].setGroup(container);
//            controllers[i].getCaptionLabel().hide();
//        }
//
//        /* Add the group to the inspector */
//        addChildVertically(container);
//        //indent container
//        float[] position = container.getPosition();
//        container.setPosition(position[0] + IDENTATION, position[1]);
//
//    }
//
//    private Controller[] buildControllerForProperty(Group container, PObjectProperty property, Class<?> type) {
//        System.out.println("building controller for " + property.getName() + " of type " + type.getName());
//        if (type == boolean.class) {
//            Toggle toggle = cp5.addToggle(property.getName())
//                    .setPosition(0, 0)
//                    .setSize(20, 20)
//                    .setGroup(this)
//                    .setValue((Boolean) property.getValue())
//                    .setMode(ControlP5.SWITCH);
//
//            toggle.getCaptionLabel().hide();
//
//            setupCheckboxCallback(toggle, property);
//
//            return new Controller[]{toggle};
//        } else if (type == int.class) {
//            Numberbox numberbox = cp5.addNumberbox(property.getName())
//                    .setPosition(0, 0)
//                    .setSize(100, 20)
//                    .setGroup(this)
//                    .setValue((Integer) property.getValue());
//
//            setupNumberboxCallback(numberbox, property);
//
//            return new Controller[]{numberbox};
//        } else if (type == Float.class || type == float.class) {
//            Numberbox numberbox = cp5.addNumberbox(property.getName())
//                    .setPosition(0, 0)
//                    .setSize(100, 20)
//                    .setGroup(this)
//                    .setValue((Float) property.getValue());
//
//            setupNumberboxCallback(numberbox, property);
//
//            return new Controller[]{numberbox};
//        } else if (type == String.class) {
//            Textfield textfield = cp5.addTextfield(property.getName())
//                    .setPosition(0, 0)
//                    .setSize(100, 20)
//                    .setGroup(this)
//                    .setValue((String) property.getValue());
//            textfield.setAutoClear(false);
//            textfield.getCaptionLabel().hide();
//            setupTextfieldCallback(textfield, property);
//
//            return new Controller[]{textfield};
//        } else if (type == PVector.class) {
//            Numberbox numberbox = cp5.addNumberbox(property.getName())
//                    .setPosition(0, 0)
//                    .setSize(100, 20)
//                    .setGroup(this)
//                    .setValue((Float) property.getValue());
//            return new Controller[]{numberbox};
//        } else if (type.getName().equals("[F")) {
//            float[] vector = (float[]) property.getValue();
//
//            Controller<?>[] controllers = new Controller[vector.length];
//
//            for (int i = 0; i < vector.length; i++) {
//                Numberbox numberbox = cp5.addNumberbox(property.getName() + i)
//                        .setPosition(0, 0)
//                        .setSize((container.getWidth()/2) / vector.length, 20)
//                        .setValue(vector[i]);
//                numberbox.getCaptionLabel().hide();
//
//                controllers[i] = numberbox;
//            }
//            return controllers;
//
//        }
//        else if (type.equals(Vec2.class)){
//
//            Vec2 vector = (Vec2) property.getValue();
//            float[] values = new float[]{vector.x,vector.y};
//
//            Controller<Numberbox>[] controllers = new Controller[values.length];
//            for (int i = 0; i < values.length; i++) {
//                Numberbox numberbox = cp5.addNumberbox(property.getName() + i)
//                        .setPosition(0, 0)
//                        .setSize((container.getWidth()/2) / values.length, 20)
//                        .setValue(values[i]);
//                numberbox.getCaptionLabel().hide();
//
//                controllers[i] = numberbox;
//            }
//
//            setupVec2Callback(controllers,property);
//
//            return controllers;
//        }
//
//        else if(type.equals(MusicalNote.class)){
//
//            Keyboard keyboard = new Keyboard(cp5,"keyboard"+property.getName())
//                    .setPosition(50,50)
//                    .setSize(100,20)
//                    .setGroup(this)
//                    .bringToFront()
//                    .setRange(36,48)
//                    .addCallback(callbackEvent -> {
//                        if(callbackEvent.getAction() == ControlP5.ACTION_BROADCAST){
//                            Keyboard keyboard1 = (Keyboard) callbackEvent.getController();
//                            MusicalNote note = new MusicalNote((int) keyboard1.getValue());
//                            property.setValue(note);
//                        }
//                    })
//                    ;
//
//
//
//            return new Controller[]{keyboard};
//        }
//
//
//
//        Textlabel t = new Textlabel(cp5, "Property" + property.getName());
//        //get class name
//        String className = type.getName();
//        System.out.println("class name " + className);
//
//        t.setText(className);
//        return new Controller[]{t};
//
//    }
//
//    private void buildGeneralSettings() {
//        Group generalSettingsGroup = new Group(cp5,"GeneralSettingsGroup");
//        generalSettingsGroup.setWidth(getWidth());
//        generalSettingsGroup.setBackgroundColor(Color.random());
//        generalSettingsGroup.hideBar();
//        Textlabel label = new Textlabel(cp5,"labelGeneralSettings");
//        label.setText("MIDI Output");
//        generalSettingsGroup.addChildVertically(label);
//        addChildVertically(generalSettingsGroup);
//
//        String[] midiInterfaces = MidiBus.availableOutputs();
//        DropdownList midiInputList = cp5.addDropdownList("Midi Output List")
//                .setPosition(0,0)
//                .setSize(100,100)
//                .setGroup(this)
//                .setBarHeight(20)
//                .setItemHeight(20)
//                .setType(ScrollableList.DROPDOWN)
//                .setBackgroundColor(255)
//                .close()
//                .setItems(midiInterfaces)
//                .setWidth(getWidth())
//                .addCallback(new CallbackListener() {
//                    @Override
//                    public void controlEvent(CallbackEvent callbackEvent) {
//                        if(callbackEvent.getAction() == ControlP5.ACTION_BROADCAST){
//                            int value = (int) callbackEvent.getController().getValue();
//
//                            MidiManager.getInstance().changeOutput(value);
//                            System.out.println("midi output changed to " + value);
//                        }
//                    }
//                })
//                ;
//
//        generalSettingsGroup.addChildVertically(midiInputList);
//        Button midiReset = cp5.addButton("MidiReset")
//                .addCallback(new CallbackListener() {
//                    @Override
//                    public void controlEvent(CallbackEvent callbackEvent) {
//                        if(callbackEvent.getAction() == ControlP5.ACTION_BROADCAST){
//                            System.out.println("midi reset");
//                            MidiBus midiBus = MidiManager.getInstance().getMidiBus();
//                            midiBus.clearInputs();
//                            midiBus.clearOutputs();
//                            midiBus.addInput(midiInputList.getItem((int) midiInputList.getValue()).get("name").toString());
//                            midiBus.addOutput(midiInputList.getItem((int) midiInputList.getValue()).get("name").toString());
//                        }
//                    }
//                })
//                ;
//        generalSettingsGroup.addChildVertically(midiReset);
//
//
//
//    }
//
//    private void setupCheckboxCallback(Toggle toggle, PObjectProperty property) {
//        toggle.addCallback(new CallbackListener() {
//            @Override
//            public void controlEvent(CallbackEvent callbackEvent) {
//                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
//                    boolean value = toggle.getState();
//                    property.setValue(value);
//                }
//            }
//        });
//    }
//
//    private void setupTextfieldCallback(Textfield textfield, PObjectProperty property) {
//        textfield.addCallback(new CallbackListener() {
//            @Override
//            public void controlEvent(CallbackEvent callbackEvent) {
//                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
//                    String value = textfield.getText();
//                    property.setValue(value);
//                }
//            }
//        });
//    }
//
//    private void setupScrollableListCallback(Controller controller, PObjectProperty property) {
//        controller.addCallback(new CallbackListener() {
//            @Override
//            public void controlEvent(CallbackEvent callbackEvent) {
//
//                //on press
//                if (callbackEvent.getAction() == ControlP5.ACTION_PRESS) {
//                    controller.bringToFront();
//                }
//
//                ScrollableList controller = (ScrollableList) callbackEvent.getController();
//
//                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
//                    int index = (int) controller.getValue();
//                    Object value = controller.getItem(index).get("value");
//                    System.out.println("property set to " + value);
//                    property.setValue(value);
//                }
//            }
//        });
//    }
//
//
//    private void setupNumberboxCallback(Numberbox numberbox, PObjectProperty property) {
//        numberbox.addCallback(new CallbackListener() {
//            @Override
//            public void controlEvent(CallbackEvent callbackEvent) {
//                if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
//                    int value = (int) numberbox.getValue();
//                    property.setValue(value);
//                }
//            }
//        });
//    }
//
//    private void setupVec2Callback(Controller<Numberbox>[] numberboxes,PObjectProperty property){
//        //this function will setup the callback on numberbox to update the vector property
//        for (int i = 0; i < numberboxes.length; i++) {
//            Controller<Numberbox> numberbox = numberboxes[i];
//            numberbox.addCallback(new CallbackListener() {
//                @Override
//                public void controlEvent(CallbackEvent callbackEvent) {
//                    if (callbackEvent.getAction() == ControlP5.ACTION_BROADCAST) {
//                        Vec2 vec2 = new Vec2(numberboxes[0].getValue(),numberboxes[1].getValue());
//                        property.setValue(vec2);
//                    }
//                }
//            });
//        }
//    }

}
