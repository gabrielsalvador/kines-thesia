package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.utils.MathUtils;
import me.gabrielsalvador.utils.Vector;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;

public class PlayableNoteView implements View<PObject> {

    private PObject _model;
    private BodyComponent _body;

    public PlayableNoteView(PObject model) {
        _model = model;
        _body = _model.getComponent(BodyComponent.class);
        _model.setView(this);
    }

    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        Vec2 position = _body.getPosition();
        Shape s = _body.getShape();

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
      return _body.getShape().isMouseOver(mouseX, mouseY, _body.getPosition().x, _body.getPosition().y);
    }

    public Vector getPosition() {
        Vec2 pos = _body.getPosition();
        return new Vector(pos.x, pos.y);
    }
}
