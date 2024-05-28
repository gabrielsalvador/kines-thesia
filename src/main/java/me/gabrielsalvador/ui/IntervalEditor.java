package me.gabrielsalvador.ui;

import controlP5.*;
import controlP5.layout.LayoutBuilder;
import me.gabrielsalvador.core.App;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.utils.Interval;

import java.util.ArrayList;
import java.util.function.Function;

public class IntervalEditor extends PropertyEditor  {

    private final ArrayList<PObjectProperty> pProperties;

    Keyboard keyboard;
    Button upButton = new Button(App.getInstance().getCP5(), "upButton");
    Button downButton = new Button(App.getInstance().getCP5(), "downButton");
    Textlabel title = new Textlabel(App.getInstance().getCP5(), "set note").setText("Set Note");
    int rangeLow = 20;
    int rangeHigh = 34;
    int step = 12;

    public IntervalEditor(ControlP5 theControlP5, String theName, ArrayList<Object> _pProperties) throws Exception {
        super(theControlP5, theName);
        pProperties = new ArrayList<>();
        for (Object obj : _pProperties) {
            pProperties.add((PObjectProperty) obj);
        }

        //keyboard
        keyboard = new Keyboard(theControlP5, "keyboard");
        keyboard.setRange(20, 34);
        keyboard.moveTo(this);
        keyboard.addListenerFor(ControlP5Constants.ACTION_PRESS, event -> keyboardPressed(event));
        highlight();

        //up/down buttons
        upButton.setLabel("▲").moveTo(this);
        upButton.addListenerFor(ControlP5Constants.ACTION_PRESS, event -> upPressed());
        downButton.moveTo(this).setLabel("▼");
        downButton.addListenerFor(ControlP5Constants.ACTION_PRESS, event -> downPressed());

        //title
        title.moveTo(this);


        //parent
        setHeight(200);

    }

    public void highlight() {
        int[] highlightedKeys = new int[pProperties.size()];
        pProperties.forEach(p-> {
            Interval interval = (Interval) p.getValue();
            highlightedKeys[pProperties.indexOf(p)] = interval.interval;
        });
        keyboard.setHighlightedKeys(highlightedKeys);

    }


    @Override
    public Group setWidth(int theWidth) {
        super.setWidth(theWidth);
        return this;
    }

    @Override
    public Group setHeight(int theHeight) {
        super.setHeight(theHeight);
        return this;
    }


    public void resize(int theWidth, int theHeight) {

        setHeight(theHeight);

        keyboard.setPosition(0,20);
        keyboard.setWidth(theWidth-20);
        keyboard.setHeight(theHeight-20);


        upButton.setPosition(theWidth - 20, 20);
        upButton.setHeight(-10 + theHeight / 2);
        upButton.setWidth(20);
        downButton.setPosition(theWidth - 20, theHeight / 2 + 10);
        downButton.setHeight(-10+theHeight / 2);
        downButton.setWidth(20);

    }


    public void upPressed() {
        rangeLow += step;
        rangeHigh += step;

        if (rangeHigh > 127) {
            rangeLow = 127 - 14;
            rangeHigh = 127;
        }
        keyboard.setRange(rangeLow, rangeHigh);
        highlight();
    }

    public void downPressed() {
        rangeLow -= step;
        rangeHigh -= step;

        if (rangeLow < 0) {
            rangeLow = 0;
            rangeHigh = 14;
        }
        keyboard.setRange(rangeLow, rangeHigh);
        highlight();

    }

    public void keyboardPressed(CallbackEvent event) {
        int note = (int) event.getController().getValue();
        keyboard.setHighlightedKeys(new int[]{note});
        for (PObjectProperty pProperty : pProperties) {
            pProperty.setValue(note);
        }
    }
}
