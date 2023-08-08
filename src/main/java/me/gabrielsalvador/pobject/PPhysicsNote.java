package me.gabrielsalvador.pobject;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.views.PlayableNoteView;
import me.gabrielsalvador.pobject.views.View;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import processing.core.PGraphics;



public class PPhysicsNote extends PObject{
    private Body _body;

    public PPhysicsNote(Vec2 position){
        _body = PhysicsManager.getInstance().createCircle(position, Config.PHYSICS_NOTE_DEFAULT_SIZE);
        initialize();
    }

    /*TODO: add this to PPObject class, so its a standard for every child*/
    private void initialize() {
        setView(new PlayableNoteView(this));
    }

    @Override
    public void onEnter(int x, int y) {

    }

    @Override
    public void onLeave(int x, int y) {

    }

    @Override
    public float[] getPosition() {
        Vec2 pos = _body.getPosition();
        return new float[]{pos.x,pos.y};
    }

    public Body getBody(){
        return _body;
    }

}


class PPhysicsNoteView implements View<PPhysicsNote>{

    private PPhysicsNote _model;

    @Override
    public PPhysicsNote getModel() {
        return _model;
    }

    @Override
    public void display(PGraphics graphics) {
        Body body = getModel().getBody();
        org.jbox2d.common.Vec2 position = body.getPosition();

        // Assuming the body's fixture is a circle. This needs error checking in a real-world scenario.
        float radius = ((org.jbox2d.collision.shapes.CircleShape) body.getFixtureList().getShape()).m_radius;

        graphics.pushMatrix();
        graphics.translate(position.x, position.y);
        graphics.ellipse(0, 0, radius * 2, radius * 2);
        graphics.popMatrix();
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        org.jbox2d.common.Vec2 position = _model.getBody().getPosition();
        Vec2 mouse = new Vec2(mouseX,mouseY);
        float distance = org.jbox2d.common.MathUtils.distanceSquared(position, mouse);

        // Assuming circle shape for simplicity
        float radius = ((org.jbox2d.collision.shapes.CircleShape) _model.getBody().getFixtureList().getShape()).m_radius;

        return distance <= radius * radius;
    }
}

