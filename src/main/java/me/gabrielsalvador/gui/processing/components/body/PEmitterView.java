package me.gabrielsalvador.gui.processing.components.body;

import me.gabrielsalvador.core.Component;
import me.gabrielsalvador.core.HologramBody;
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
