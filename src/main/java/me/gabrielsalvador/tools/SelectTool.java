package me.gabrielsalvador.tools;

import java.util.Set;


import me.gabrielsalvador.Config;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject.PObject;
import me.gabrielsalvador.ui.views.ViewInterface;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

public class SelectTool extends Tool {

    private PVector startPoint = null;
    private PVector endPoint = null;

    public SelectTool() {

    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(int x, int y) {
        System.out.println("onClick");
        Set<PObject> pobjects = AppState.getInstance().getPObjects();
        for (PObject pobject : pobjects) {

            ViewInterface<PObject> view = pobject.getView();
            if (view.isMouseOver(x, y)) {
                // controller.setSelected(true);
                System.out.println("Selected");
            } else {
                // controller.setSelected(false);
                System.out.println("Not selected");
            }
        }
    }




    public void onPress(int x, int y) {

    }


}
