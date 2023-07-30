package me.gabrielsalvador.pobject;

import me.gabrielsalvador.views.View;
import processing.core.PGraphics;



public class PEmitterView extends PObjectView implements View<PObject> {

    private PObject _model;

    public PEmitterView(PObject model) {
        super(model);
        _model = model;
        _model.setView(this);
    }

    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        super.display(graphics);
        float[] position = _model.getPosition();

// Define the size of the emitter
        float emitterDiameter = Defaults.DEFAULT_EMITTER_SIZE;

        float offsetX = position[0] - emitterDiameter / 2;
        float offsetY = position[1] - emitterDiameter / 2;

// Change stroke color when selected
        if (_model.getIsSelected()) {
            graphics.stroke(255, 0, 0);  // Red stroke
        } else {
            graphics.stroke(0);  // Black stroke
        }

// Draw the emitter
        graphics.fill(255);  // White
        graphics.ellipse(offsetX, offsetY, emitterDiameter, emitterDiameter);

    }


    public boolean isMouseOver(int mouseX, int mouseY) {
        float[] position = _model.getPosition();

        float keyWidth = Defaults.DEFAULT_KEYBOARD_SIZE[0];
        float keyHeight = Defaults.DEFAULT_KEYBOARD_SIZE[1];

        float offsetX = position[0] - keyWidth / 2;
        float offsetY = position[1] - keyHeight / 2;

        return mouseX >= offsetX && mouseX <= offsetX + keyWidth
                && mouseY >= offsetY && mouseY <= offsetY + keyHeight;
    }





}