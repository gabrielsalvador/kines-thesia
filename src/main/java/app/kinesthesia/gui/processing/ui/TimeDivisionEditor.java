package app.kinesthesia.gui.processing.ui;

import controlP5.*;
import app.kinesthesia.core.PObjectProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TimeDivisionEditor extends CustomGroup implements PropertyEditor{

    Textlabel label;
    int[] labels = {1,2,3,4,5,6,8,16};
    Button[] buttons = new Button[8];
    ArrayList<PObjectProperty> properties;

    public TimeDivisionEditor(ControlP5 theControlP5, String theName, ArrayList<PObjectProperty> _properties) {
        super(theControlP5, theName);
        properties = _properties;

        label = new Textlabel(theControlP5, "TimeDivisionLabel").setValueLabel("Time Division");
        label.moveTo(this);

        Set<Integer> values = getCurrentValues(properties);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(theControlP5, "button" + i);
            buttons[i].setLabel(String.valueOf(labels[i]));
            buttons[i].moveTo(this);
            buttons[i].setValue(labels[i]);
            buttons[i].addListenerFor(ACTION_PRESS, this::setTimeDivision);
            buttons[i].setSwitch(true);
            if (values.contains(labels[i])) {
                buttons[i].setOn();
            }
        }
    }

    public void setTimeDivision(CallbackEvent event) {
        int i = (int) event.getController().getValue();

        for (Button button : buttons) {
            button.setOff();
        }
       ((Button)event.getController()).setOn();

        properties.forEach(p -> {
            try {
                p.setValue(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Set<Integer> getCurrentValues(ArrayList<PObjectProperty> properties) {
        Set<Integer> values = new HashSet<>();
        properties.forEach(p -> {
            if (!values.contains(p.getValue())) {
                values.add((Integer) p.getValue());
            }
        });
        return values;
    }

    @Override
    public TimeDivisionEditor setWidth(int width) {
        super.setWidth(width);
        return this;
    }
    @Override
    public TimeDivisionEditor setHeight(int height) {
        super.setHeight(height);

        label.setPosition(0, 0).setWidth(getWidth()/2);


        return this;
    }

    @Override
    public void resize(int width, int height) {

        width = width - 20;

        label.setPosition(0, 0).setWidth(width/2);

        int buttonWidth = width / 8;
        int buttonHeight = height/2;
        int index = 0;
        for(int y = 0; y < height; y += height/2){
            for (int i = width/2; i < width; i += width/7) {
                if(index >= buttons.length) break;

                buttons[index].setPosition(i, y);
                buttons[index].setWidth(buttonWidth);
                buttons[index].setSize(buttonWidth, buttonHeight-5);

                index++;
            }
        }
        setHeight(height);
    }

    @Override
    public int getHeightForInspector() {
        return 40;
    }
}
