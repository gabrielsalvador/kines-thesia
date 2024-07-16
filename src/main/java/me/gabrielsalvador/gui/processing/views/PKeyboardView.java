package me.gabrielsalvador.gui.processing.views;

import me.gabrielsalvador.core.Component;
import me.gabrielsalvador.core.HologramBody;
import me.gabrielsalvador.gui.processing.Defaults;
import me.gabrielsalvador.gui.processing.components.body.HologramBodyView;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;



public class PKeyboardView extends HologramBodyView {

    private final HologramBody _body;
    private final BlinkingLigth _blinkingLigth;

    public PKeyboardView(Component model) {
        super( (HologramBody) model);

        _body = (HologramBody) model;
        _blinkingLigth = new BlinkingLigth();

    }


    @Override
    public Component getModel() {
        return _body;
    }



    @Override
    public void display(PGraphics graphics,Component model) {
        Vec2 position = _body.getPixelPosition();

        float[] size = _body.getShape().getBoundaries();
        float keyWidth = size[0] / 7;
        float keyHeight = size[1];

        graphics.pushStyle();
        graphics.pushMatrix();

        graphics.translate(position.x, position.y-10);



        // Change stroke color when selected
        if (_body.getOwner().getIsSelected()) {
            graphics.stroke(255, 0, 0);  // Red stroke
        } else {
            graphics.stroke(0);  // Black stroke
        }

        // draw white keys
        graphics.fill(255);  // white
        for (int i = 0; i < 7; i++) {
            graphics.rect(i * keyWidth - keyWidth * 7 / 2, -keyHeight / 2, keyWidth, keyHeight);
        }

        // draw black keys
        graphics.fill(0);  // black
        for (int i = 0; i < 7; i++) {
            if (i != 2 && i != 6) {  // skip the 3rd and 7th position for black keys
                graphics.rect(i * keyWidth + keyWidth / 2 - keyWidth * 7 / 2, -keyHeight / 2, keyWidth / 2, keyHeight / 2);
            }
        }

        graphics.translate(0, -keyHeight / 2 - 10);

        _blinkingLigth.display(graphics);

        graphics.popStyle();
        graphics.popMatrix();
    }


    public boolean isMouseOver(int mouseX, int mouseY) {
        Vec2 position = _body.getPixelPosition();

        float keyWidth = Defaults.DEFAULT_KEYBOARD_SIZE[0];
        float keyHeight = Defaults.DEFAULT_KEYBOARD_SIZE[1];

        float offsetX = position.x - keyWidth / 2;
        float offsetY = position.y - keyHeight / 2;

        return mouseX >= offsetX && mouseX <= offsetX + keyWidth
                && mouseY >= offsetY && mouseY <= offsetY + keyHeight;
    }


    public BlinkingLigth getBlinkingLigth() {
        return _blinkingLigth;
    }
}