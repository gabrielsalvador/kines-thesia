package app.kinesthesia.gui.processing.tools;

import app.kinesthesia.core.Kinesthesia;
import app.kinesthesia.core.PObject;
import app.kinesthesia.gui.processing.PRubberbandPreset;
import app.kinesthesia.gui.processing.PObjectPreset;
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

        Kinesthesia.getInstance().queueModification(() -> {

            PObjectPreset preset = new PRubberbandPreset(initialPositionSnapshot, finalPositionSnapshot, _howManyResonators % 7);
            System.out.println("Creating resonator with scale note " + _howManyResonators % 7);
            PObject p = preset.create()[0];
            Kinesthesia.getInstance().addPObjectImmiadiately(p);
        });


        _initialPosition = null;
        _finalPosition = null;
        howManyResonators++;


    }




}
