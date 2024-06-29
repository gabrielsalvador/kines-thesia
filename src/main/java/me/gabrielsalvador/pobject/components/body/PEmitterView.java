package me.gabrielsalvador.pobject.components.body;

import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.shape.AbstractShape;
import me.gabrielsalvador.pobject.components.body.shape.PShape;
import me.gabrielsalvador.pobject.components.body.shape.RectanglePShape;
import processing.core.PGraphics;

public class PEmitterView extends HologramBodyView{
    public PEmitterView(HologramBody hologramBody) {
        super(hologramBody);
    }

    @Override
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
