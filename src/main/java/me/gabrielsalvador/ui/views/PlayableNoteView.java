package me.gabrielsalvador.ui.views;

import me.gabrielsalvador.model.PObject.PObject;
import me.gabrielsalvador.utils.MathUtils;
import me.gabrielsalvador.utils.Vector;
import processing.core.PApplet;
import processing.core.PGraphics;

public class PlayableNoteView extends PObjectView implements ViewInterface<PObject> {

    private PObject _model;

    public PlayableNoteView(PObject model) {
        _model = model;
        _model.setView(this);
    }

    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        Vector position = getPosition();
        float radius = getSize();

        graphics.ellipseMode(PApplet.CENTER);
        // draw a ball at the position
        graphics.pushStyle();
        graphics.strokeWeight(1);
        graphics.stroke(0);
        graphics.fill(255, 255, 255);
        graphics.ellipse(position.getX(), position.getY(), radius, radius);
        graphics.popStyle();

    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        Vector position = getPosition();
        float radius = getSize();

        // inside the circle
        float d = MathUtils.distance(mouseX, mouseY, position.getX(), position.getY());
        if (d < radius) {
            return true;
        }

        return false;

    }

    public Vector getPosition() {
        return new Vector(_model.getPosition()[0], _model.getPosition()[1]);
    }

    // the radius of the circle
    public float getSize() {
        return _model.getSize()[0];
    }
}