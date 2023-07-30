package me.gabrielsalvador.pobject.routing;


import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.views.View;
import processing.core.PApplet;
import processing.core.PGraphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class PatchSocket extends PObject implements Serializable {

    private final int SOCKET_SIZE = 10;
    private final int Y_OFFSET = 20;
    private final PObject owner;

    public PatchSocket( PObject owner) {
        this.owner = owner;
        this.setView(new PatchSocketView(owner));
    }

    @Override
    public void onPressed(int x, int y) {
        super.onPressed(x, y);
        System.out.println("PatchSocket onPressed");
    }
    @Override
    public void onHover(int mouseX, int mouseY) {
        System.out.println("PatchSocket onHover");
        Sinesthesia.getInstance().cursor(PApplet.HAND);
    }
    @Override
    public void onLeave(int mouseX, int mouseY) {
        System.out.println("PatchSocket onLeave");
        Sinesthesia.getInstance().cursor(PApplet.ARROW);
    }

    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PatchSocketView(owner));

    }

    public PObject getOwner() {
        return owner;
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
            //if selected change color
            if (_model.getIsSelected()) {
                graphics.fill(255, 0, 0);
            } else {
                graphics.fill(0);
            }
            float[] position = (float[])_model.getProperty("position").getValue();
            graphics.fill(255, 255,255);
            graphics.pushMatrix();
            graphics.translate(position[0], position[1]);
            graphics.ellipseMode(PApplet.CENTER);
            graphics.ellipse(0, Y_OFFSET,SOCKET_SIZE,SOCKET_SIZE);
            graphics.popMatrix();
        }

        @Override
        public boolean isMouseOver(int mouseX, int mouseY) {
            float[] position = (float[])_model.getProperty("position").getValue();
            float centerX = position[0];
            float centerY = position[1] + Y_OFFSET;

            float distance = PApplet.dist(mouseX, mouseY, centerX, centerY);
            boolean isOver =  distance <= SOCKET_SIZE / 2;
            return isOver;
        }
    }
}

