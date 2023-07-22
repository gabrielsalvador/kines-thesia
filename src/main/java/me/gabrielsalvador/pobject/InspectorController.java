package me.gabrielsalvador.pobject;

import controlP5.Bang;
import controlP5.ControlP5;
import controlP5.Group;
import me.gabrielsalvador.core.Sinesthesia;

public class InspectorController extends Group {
    public InspectorController(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
    }

    @Override
    public void didSetupLayout() {
        for (int i = 0; i < 10; i++) {
            addChildHorizontally(new Bang(cp5, "bang"+i));
        }
    }




}
