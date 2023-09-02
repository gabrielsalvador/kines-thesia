package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.utils.Vector;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;

public class PlayableNoteView implements View<PObject> {

    private PObject _model;


    public PlayableNoteView(PObject model) {
        _model = model;
        _model.setView(this);
    }

    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        BodyComponent bodyComponent = _model.getBodyComponent();
        Vec2 position = bodyComponent.getPosition();
        Shape s = _model.getBodyComponent().getShape();

        graphics.ellipseMode(PApplet.CENTER);
        graphics.pushStyle();
        graphics.strokeWeight(1);
        if (_model.getIsSelected()) {
            graphics.stroke(Config.THEME_COLOR_SELECTED);
            graphics.fill(Config.THEME_COLOR_SELECTED);
        } else {
            graphics.stroke(0);
            graphics.fill(255, 255, 255);
        }
        s.display(graphics, position.x, position.y);
        graphics.popStyle();
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        PhysicsBodyComponent bodyComponent = _model.getBodyComponent();
        Vec2 position = bodyComponent.getPixelPosition();
        Shape s = bodyComponent.getShape();
        boolean result = s.isMouseOver(mouseX, mouseY, position.x, position.y);
        return result;

    }


}
