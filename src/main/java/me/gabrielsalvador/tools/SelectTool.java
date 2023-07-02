package me.gabrielsalvador.tools;

import java.util.Set;

import me.gabrielsalvador.controllers.PObjectController;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject.PObject;
import me.gabrielsalvador.model.PObject.PlayableNote;
import me.gabrielsalvador.ui.views.PObjectView;
import me.gabrielsalvador.ui.views.ViewInterface;

public class SelectTool extends Tool{

    @Override
    public void onEnter() {
        
    }

    @Override
    public void onLeave() {
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onReleaseOutside() {
    }

    @Override
    public void onPress() {
    }

    @Override
    public void onDrag() {
    }

    @Override
    public void onMove() {
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

    @Override
    public void onScroll() {
    }

    @Override
    public void onKey() {
    }
    
}
