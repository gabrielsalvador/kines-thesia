package me.gabrielsalvador.tools;

import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObject;
import processing.event.KeyEvent;

@DisplayName("Eraser")
public class EraserTool extends Tool{
    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public boolean onPressed(PObject pObject, int[] mousePosition) {

            if (pObject == null) {
                return false;
            }

            AppController.getInstance().queueModification(() -> {
                AppController.getInstance().removePObjectImmediatly(pObject);
            });

        return false;
    }

    @Override
    public void onRelease(PObject pObject) {

    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }
}
