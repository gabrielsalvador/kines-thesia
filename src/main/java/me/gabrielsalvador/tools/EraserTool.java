package me.gabrielsalvador.tools;

import me.gabrielsalvador.pobject.PObject;
import processing.event.KeyEvent;

public class EraserTool extends Tool{
    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public void onPressed(PObject pObject, int[] mousePosition) {
        if(pObject != null)
            pObject.remove();
    }

    @Override
    public void onRelease(PObject pObject) {

    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }
}
