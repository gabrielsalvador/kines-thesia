package me.gabrielsalvador.pobject.routing;

import me.gabrielsalvador.pobject.PKeyboardView;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.routing.annotations.InletAnnotation;
import me.gabrielsalvador.views.View;
import processing.core.PGraphics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class PatchSocket extends PObject implements Serializable {
    private final PObject owner;

    public PatchSocket( PObject owner) {
        this.owner = owner;
        this.setView(new PatchSocketView(this));
    }

    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PatchSocketView(owner));

    }

    private class PatchSocketView implements View<PObject> {

        private  PObject _model;
        public PatchSocketView(PObject model) {
            _model = model;
        }

        @Override
        public PObject getModel() {
            return _model;
        }

        @Override
        public void display(PGraphics graphics) {
            float[] position = (float[])_model.getProperty("position").getValue();
            graphics.pushMatrix();
            graphics.translate(position[0], position[1]);
            graphics.ellipse(0, 15,3,3);
            graphics.popMatrix();
        }

        @Override
        public boolean isMouseOver(int mouseX, int mouseY) {
            return false;
        }
    }
}

