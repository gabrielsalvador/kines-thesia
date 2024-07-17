package app.kinesthesia.gui.processing.tools;

import app.kinesthesia.core.Kinesthesia;
import app.kinesthesia.core.PObject;
import app.kinesthesia.gui.processing.DisplayName;
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

            Kinesthesia.getInstance().queueModification(() -> {
                Kinesthesia.getInstance().removePObjectImmediatly(pObject);
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
