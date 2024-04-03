package me.gabrielsalvador.tools;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObjectPreset;
import me.gabrielsalvador.pobject.PRubberbandPreset;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.Body;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import me.gabrielsalvador.pobject.PObject;

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
