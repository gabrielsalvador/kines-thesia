package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.BodyComponent;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class PEmitterView  implements View<PObject> {

    private PObject _model;
    private BodyComponent _body;

    public PEmitterView(PObject model) {
        _model = model;
        _body = _model.getComponent(BodyComponent.class);
        _model.setView(this);
    }

    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        graphics.pushStyle();
        Vec2 position = _body.getPixelPosition();

        // Get the width and height of the emitter shape
        Shape shape = _body.getShape();
        float size[] = shape.getBoundayBox();
        float halfWidth = size[0] / 2.0f;
        float halfHeight = size[1] / 2.0f;

        // Adjust the position so that the top-leftmost point is at the center
        float adjustedX = position.x - halfWidth;
        float adjustedY = position.y - halfHeight;

        // Set color when active
        if (_model.getIsSelected()) {
            graphics.stroke(255, 0, 0);  // Red stroke
        } else {
            graphics.stroke(0);  // Black stroke
        }

        // Draw emitter
        graphics.fill(255);  // white
        shape.display(graphics, adjustedX, adjustedY);
        graphics.popStyle();
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        Vec2 position = _body.getPixelPosition();

        // Get the width and height of the emitter shape
        RectangleShape shape = (RectangleShape) _body.getShape();
        float size[] = shape.getBoundayBox();
        float halfWidth = size[0] / 2.0f;
        float halfHeight = size[1] / 2.0f;

        // Adjust the position so that the top-leftmost point is at the center
        float adjustedX = position.x - halfWidth;
        float adjustedY = position.y - halfHeight;

        return shape.isMouseOver(mouseX, mouseY, adjustedX, adjustedY);
    }

}
