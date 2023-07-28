package me.gabrielsalvador.pobject.routing;


import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PGraphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class PatchSocket extends PObject implements Serializable {

    private final int SOCKET_SIZE = 5;
    private final int Y_OFFSET = 10;
    private final PObject owner;

    public PatchSocket( PObject owner) {
        this.owner = owner;
        this.setView(new PatchSocketView(owner));
    }

    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PatchSocketView(owner));

    }

    private class PatchSocketView  implements View<PObject> {

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
            graphics.fill(255, 255,255);
            graphics.pushMatrix();
            graphics.translate(position[0], position[1]);
            graphics.ellipse(0, Y_OFFSET,SOCKET_SIZE,SOCKET_SIZE);
            graphics.popMatrix();
        }

        @Override
        public boolean isMouseOver(int mouseX, int mouseY) {
            float[] position = (float[])_model.getProperty("position").getValue();
            float d = (float) Math.sqrt(Math.pow(mouseX - position[0], 2) + Math.pow(mouseY - position[1], 2));
            return d < SOCKET_SIZE;
        }
    }
}

