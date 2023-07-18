package me.gabrielsalvador.tools;

import java.util.Set;


import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject.PObject;
import me.gabrielsalvador.ui.views.ViewInterface;
import processing.event.KeyEvent;

public class SelectTool extends Tool{


    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(int x, int y) {
        Set<PObject> pobjects = AppState.getInstance().getPObjects();
        for (PObject pobject : pobjects) {
            
            ViewInterface<PObject> view = pobject.getView();
            if (view.isMouseOver( x, y)) {
                // controller.setSelected(true);
                System.out.println("Selected");
            } else {
                // controller.setSelected(false);
                System.out.println("Not selected");
            }
        }
    }


}
