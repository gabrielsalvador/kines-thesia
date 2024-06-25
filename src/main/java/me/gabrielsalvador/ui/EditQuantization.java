package me.gabrielsalvador.ui;

import controlP5.ControlP5;
import controlP5.Textlabel;
import me.gabrielsalvador.pobject.PObjectProperty;

import java.util.ArrayList;

public class EditQuantization extends CustomGroup implements PropertyEditor{

    KSlider slider;
    Textlabel label;
    public EditQuantization(ControlP5 cp5, String theName, ArrayList<PObjectProperty> properties) {
        super(cp5, theName);
        slider = (KSlider) new KSlider(cp5, theName+ "slider", properties).moveTo(this);

        label = cp5.addTextlabel(theName + "Label")
                .setText("Quantization Amount: ")
                .moveTo(this);

        label.get().align(ControlP5.LEFT, ControlP5.TOP).setPaddingX(0).setPaddingY(0);
    }
    @Override
    public int getHeightForInspector() {
        return 20;
    }

    @Override
    public EditQuantization setWidth(int theWidth) {
        super.setWidth(theWidth);
        label.setPosition(0, 0);
        slider.setPosition((float) theWidth /2, 0);
        return this;
    }

    @Override
    public EditQuantization setHeight(int theHeight) {
        super.setHeight(theHeight);
        label.setPosition(0, 0).setHeight(theHeight).setWidth(getWidth()/2 );
        slider.setPosition((float) getWidth()/2 , 0).setHeight(theHeight).setWidth(getWidth()/2 );
        return this;
    }


    @Override
    public void resize(int width, int height) {
        setWidth(width);
        setHeight(height);

    }
}
