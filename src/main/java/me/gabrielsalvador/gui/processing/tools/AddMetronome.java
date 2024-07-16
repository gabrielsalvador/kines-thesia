package me.gabrielsalvador.gui.processing.tools;

;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.DisplayName;
import me.gabrielsalvador.gui.processing.PMetronome;
import me.gabrielsalvador.gui.processing.SkipProcessing;
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

        AppController.getInstance().queueModification(new Runnable() {
            @Override
            public void run() {
                PMetronome metronome = new PMetronome();
                metronome.getBodyComponent().setPosition(mousePosition[0], mousePosition[1]);
                AppController.getInstance().addPObject(metronome);
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
