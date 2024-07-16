package me.gabrielsalvador.gui.processing.tools;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.PObjectPreset;
import me.gabrielsalvador.gui.processing.PRubberbandPreset;
import org.jbox2d.common.Vec2;

public class SpringTool extends AddResonatorTool {



    @Override
    public void onRelease(PObject pObject) {

        if (_initialPosition == null || _finalPosition == null) {
            return;
        }

        // Create snapshots of the current state inside the lambda
        Vec2 initialPositionSnapshot = new Vec2(_initialPosition.x, _initialPosition.y);
        Vec2 finalPositionSnapshot = new Vec2(_finalPosition.x, _finalPosition.y);
        int _howManyResonators = AddResonatorTool.howManyResonators;

        AppController.getInstance().queueModification(() -> {

            PObjectPreset preset = new PRubberbandPreset(initialPositionSnapshot, finalPositionSnapshot, _howManyResonators % 7);
            System.out.println("Creating resonator with scale note " + _howManyResonators % 7);
            PObject p = preset.create()[0];
            AppController.getInstance().addPObjectImmiadiately(p);
        });


        _initialPosition = null;
        _finalPosition = null;
        howManyResonators++;


    }




}
