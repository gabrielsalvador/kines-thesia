package me.gabrielsalvador.pobject;

import me.gabrielsalvador.views.View;
import processing.core.PGraphics;

public class PEmitterView extends PObjectView implements View<PObject> {

    private PObject _model;

    public PEmitterView(PObject model) {
        _model = model;
        _model.setView(this);
    }

    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        float[] position = _model.getPosition();

        // Set color when active
        if (_model.getIsSelected()) {
            graphics.stroke(255, 0, 0);  // Red stroke
        } else {
            graphics.stroke(0);  // Black stroke
        }

        // Draw emitter
        graphics.fill(255);  // white
        graphics.ellipse(position[0], position[1], 20, 20);

    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        float[] position = _model.getPosition();

        // Assuming the emitter is represented as a point
        // Change this if your emitter has a different representation
        return mouseX == position[0] && mouseY == position[1];
    }
}
