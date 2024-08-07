package app.kinesthesia.gui.processing.tools;



import app.kinesthesia.core.Kinesthesia;
import app.kinesthesia.core.PObject;
import app.kinesthesia.gui.processing.DisplayName;
import app.kinesthesia.gui.processing.PObjectPreset;
import org.jbox2d.common.Vec2;
import processing.event.KeyEvent;


@DisplayName("Emitter")
public class AddPEmitter extends Tool{
    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {


    }

    @Override
    public boolean onPressed(PObject pObject, int[] mousePosition) {
        if (pObject != null ) return true;

        Kinesthesia app = Kinesthesia.getInstance();
        Vec2 mousePos = new Vec2(mousePosition[0], mousePosition[1]);
        PObject[] objs = new PObjectPreset.EmitterPreset(mousePos).create();
        for (PObject p : objs){
            Kinesthesia.getInstance().addPObject(p);
        }

        return false;

    }

    @Override
    public void onRelease(PObject pObject) {

    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }
}
