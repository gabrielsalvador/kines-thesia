package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.Defaults;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;



public class PKeyboardView  implements View<PObject> {

    private PObject _model;
    private BodyComponent _body;


    public PKeyboardView(PObject model) {
        _model = model;
        _body = (BodyComponent) _model.getComponent(BodyComponent.class);
        _model.setView(this);
    }

    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        Vec2 position = _body.getPixelPosition();

        float[] size = (float[]) ((RectangleShape )_body.getShape()).getBoundayBox();
        float keyWidth = size[0] / 7;
        float keyHeight = size[1];

        float offsetX = position.x - keyWidth * 7 / 2;
        float offsetY = position.y - keyHeight / 2;

        graphics.pushStyle();
        graphics.pushMatrix();
        // Change stroke color when selected
        if (_model.getIsSelected()) {
            graphics.stroke(255, 0, 0);  // Red stroke
        } else {
            graphics.stroke(0);  // Black stroke
        }

        // draw white keys
        graphics.fill(255);  // white
        for (int i = 0; i < 7; i++) {
            graphics.rect(offsetX + i * keyWidth, offsetY, keyWidth, keyHeight);
        }

        // draw black keys
        graphics.fill(0);  // black
        for (int i = 0; i < 7; i++) {
            if (i != 2 && i != 6) {  // skip the 3rd and 7th position for black keys
                graphics.rect(offsetX + i * keyWidth + keyWidth / 2, offsetY, keyWidth / 2, keyHeight / 2);
            }
        }

        graphics.popStyle();
        graphics.popMatrix();
    }


    public boolean isMouseOver(int mouseX, int mouseY) {

        Vec2 pixelPosition = _body.getPixelPosition();
        float keyWidth = Defaults.DEFAULT_KEYBOARD_SIZE[0];
        float keyHeight = Defaults.DEFAULT_KEYBOARD_SIZE[1];

        float offsetX = pixelPosition.x - keyWidth / 2;
        float offsetY = pixelPosition.y - keyHeight / 2;

        return mouseX >= offsetX && mouseX <= offsetX + keyWidth
                && mouseY >= offsetY && mouseY <= offsetY + keyHeight;
    }






}