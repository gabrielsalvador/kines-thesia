package app.kinesthesia.gui.processing.components.body;

import app.kinesthesia.core.Component;
import app.kinesthesia.core.HologramBody;
import processing.core.PGraphics;

public class PEmitterView extends HologramBodyView{
    public PEmitterView(HologramBody hologramBody) {
        super(hologramBody);
    }


    public void display(PGraphics graphics, Component model) {
        if(!(model instanceof HologramBody body)){
            throw new IllegalArgumentException("Model must be a HologramBody");
        }
        super.display(graphics, model);
        //draw an X edge to edge
        graphics.pushMatrix();
        graphics.translate(body.getPixelPosition().x, body.getPixelPosition().y);


        graphics.popMatrix();


    }
}
