package app.kinesthesia.gui.processing.tools;

;
import app.kinesthesia.core.Kinesthesia;
import app.kinesthesia.core.PObject;
import app.kinesthesia.gui.processing.DisplayName;
import app.kinesthesia.gui.processing.PMetronome;
import app.kinesthesia.gui.processing.SkipProcessing;
import processing.event.KeyEvent;

@DisplayName("Metronome")
@SkipProcessing()
public class AddMetronome extends Tool {

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public boolean onPressed(PObject pObject, int[] mousePosition) {

        Kinesthesia.getInstance().queueModification(new Runnable() {
            @Override
            public void run() {
                PMetronome metronome = new PMetronome();
                metronome.getBodyComponent().setPosition(mousePosition[0], mousePosition[1]);
                Kinesthesia.getInstance().addPObject(metronome);
            }
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
