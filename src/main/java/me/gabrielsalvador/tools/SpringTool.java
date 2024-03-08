package me.gabrielsalvador.tools;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.Body;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import me.gabrielsalvador.pobject.PObject;

public class SpringTool extends Tool {

    private PhysicsBodyComponent firstBody;

    public SpringTool() {
        super();
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {
        // Implement the method here
    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public boolean onPressed(PObject pObject, int[] mousePosition) {

        if (pObject == null) { return true; }

        PhysicsBodyComponent bodyComponent = pObject.getComponent(BodyComponent.class);
        firstBody = bodyComponent;

        return false;
    }

    @Override
    public void onRelease(PObject pObject) {

        if(pObject == null) {
            firstBody = null;
            return;
        }

        PhysicsBodyComponent pBody = pObject.getComponent(BodyComponent.class);

        Body secondBody = pBody.getJBox2DBody();

        if (firstBody != null && secondBody != null) {
            addSpring(firstBody.getJBox2DBody(), secondBody);
            firstBody = null;
        }
    }

    @Override
    public void draw(PGraphics graphics) {
        if (firstBody != null) {
            int[] mousePos = AppController.getInstance().getCanvas().getMousePosition();
            graphics.stroke(255, 0, 0);
            graphics.strokeWeight(1);
            graphics.line(firstBody.getPosition().x, firstBody.getPixelPosition().y, mousePos[0], mousePos[1]);

        }

    }

    private void addSpring(Body bodyA, Body bodyB) {
        DistanceJointDef jointDef = new DistanceJointDef();

        // Set the two bodies
        jointDef.bodyA = bodyA;
        jointDef.bodyB = bodyB;

        // Set the length of the spring equal to the distance between the two bodies
        jointDef.length = bodyA.getWorldCenter().sub(bodyB.getWorldCenter()).length();

        // Set the damping ratio (0 = no damping, 1 = critical damping)
        jointDef.dampingRatio = 0.5f;

        // Set the frequency in hertz (0 = no oscillation, higher values = more oscillation)
        jointDef.frequencyHz = 5.0f;

        // Create the joint
        DistanceJoint joint = (DistanceJoint) bodyA.getWorld().createJoint(jointDef);
    }
}
