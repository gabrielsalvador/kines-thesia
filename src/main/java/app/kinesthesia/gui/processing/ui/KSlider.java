package app.kinesthesia.gui.processing.ui;

import controlP5.*;
import app.kinesthesia.core.PObjectProperty;

import java.util.ArrayList;

public class KSlider extends CustomGroup implements PropertyEditor {
    Slider slider;
    private ArrayList<PObjectProperty> properties;

    public KSlider(ControlP5 theControlP5, String theName, ArrayList<PObjectProperty> properties) {
        super(theControlP5, theName);

        this.properties = properties;

        float currentValue = (float) properties.get(0).getValue();
        slider = cp5.addSlider(theName)
                .setRange(0, 1)
                .setValue(currentValue)
                .setPosition(10, 10)
                .setWidth(200)
                .setHeight(20)
                .addListener(this)
                .moveTo(this);
        slider.getValueLabel().hide();
        slider.getCaptionLabel().hide();

        setHeight(20);
    }

    public KSlider(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
    }


    @Override
    public void controlEvent(ControlEvent theEvent) {
        properties.forEach(p -> {
                p.setValue(theEvent.getValue());
        });
    }


    @Override
    public Group setWidth(int theWidth) {
        super.setWidth(theWidth);
        slider.setWidth(theWidth);
        slider.setPosition(0,0);

        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);
        slider.setHeight(theHeight);
        slider.setPosition(0,0);
        return this;
    }


    @Override
    public void resize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    @Override
    public int getHeightForInspector() {
        return 30;
    }
}
