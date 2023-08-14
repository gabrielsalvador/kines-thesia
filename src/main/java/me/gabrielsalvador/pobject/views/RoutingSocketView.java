package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.PSocket;
import me.gabrielsalvador.pobject.routing.Routable;
import me.gabrielsalvador.pobject.components.BodyComponent;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class RoutingSocketView<T extends Routable> implements View<PObject> {

    static public int SIZE_X = 7;
    PSocket<T> _model;
    private BlinkingLigth _blinkingLigth = new BlinkingLigth(SIZE_X);
    private BodyComponent _body;

    public RoutingSocketView(PSocket<T> model) {
        _model = model;
        _body = _model.getOwner().getComponent(BodyComponent.class);
    }

    @Override
    public PSocket<?> getModel() {
        return _model;
    }

    @Override
    public void display(PGraphics graphics) {
        Vec2 position = _body.getPosition();
        float[] ownerSize = ((RectangleShape)_body.getShape()).getBoundayBox();
        graphics.pushStyle();
        graphics.pushMatrix();
        graphics.translate(position.x, position.y + ownerSize[1] + SIZE_X);
        graphics.ellipseMode(PConstants.CENTER);
        graphics.fill(_model.getIsHovered() ? 127 : 255);
        graphics.ellipse(0, 0, SIZE_X + 4, SIZE_X + 4);
        _blinkingLigth.display(graphics);
        graphics.popMatrix();
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        Vec2 position = _body.getPosition();
        float[] ownerSize = ((RectangleShape)_body.getShape()).getBoundayBox();

        float centerX = position.x;
        float centerY = position.y + ownerSize[1] + SIZE_X;

        float distance = PApplet.dist(mouseX, mouseY, centerX, centerY);
        return distance <= SIZE_X / 2 + 4;
    }

    public BlinkingLigth getBlinkingLigth() {
        return _blinkingLigth;
    }
}
