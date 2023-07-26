package me.gabrielsalvador.pobject;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.views.View;
import me.gabrielsalvador.utils.MathUtils;
import me.gabrielsalvador.utils.Vector;
import processing.core.PApplet;
import processing.core.PGraphics;

public class PlayableNoteView extends PObjectView implements View<PObject> {

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
        float radius = _model.getSize()[0];

        graphics.ellipseMode(PApplet.CENTER);
        // draw a ball at the position
        graphics.pushStyle();
        graphics.strokeWeight(1);
        if(_model.getIsSelected()){
            graphics.stroke(Config.THEME_COLOR_SELECTED);
            graphics.fill(Config.THEME_COLOR_SELECTED);

        }else {
            graphics.stroke(0);
            graphics.fill(255, 255, 255);
        }
        graphics.ellipse(position.getX(), position.getY(), radius, radius);
        graphics.popStyle();

    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        Vector position = getPosition();
        float radius = _model.getSize()[0]/2;

        // inside the circle
        float d = MathUtils.distance(mouseX, mouseY, position.getX(), position.getY());
        System.out.printf("%f < %f%n", d, radius);
        if (d < Math.abs(radius)) {
            System.out.println("isMouseOVer = true");
            return true;
        }

        return false;

    }

    public Vector getPosition() {
        return new Vector(_model.getPosition()[0], _model.getPosition()[1]);
    }


}