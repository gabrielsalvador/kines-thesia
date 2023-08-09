package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PObject;
import processing.core.PGraphics;

public class PEmitterView  implements View<PObject> {

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
        float[] size = _model.getSize();
        graphics.ellipse(position[0], position[1], size[0], size[1]);

    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        float[] position = _model.getPosition();
        float[] size = _model.getSize();
        return mouseX > position[0] - size[0] / 2 && mouseX < position[0] + size[0] / 2 && mouseY > position[1] - size[1] / 2 && mouseY < position[1] + size[1] / 2;
    }
}
