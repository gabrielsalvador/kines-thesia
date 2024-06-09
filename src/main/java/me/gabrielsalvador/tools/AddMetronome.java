package me.gabrielsalvador.tools;

import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.common.SkipProcessing;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PMetronome;
import me.gabrielsalvador.pobject.PObject;
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
                metronome.getBodyComponent().setPixelPosition(mousePosition[0], mousePosition[1]);
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
