package me.gabrielsalvador.pobject.components.body;

import me.gabrielsalvador.pobject.components.Component;
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
        RectanglePShape shape = (RectanglePShape) body.getShape();
        graphics.line(-shape.getSize().x / 2, -shape.getSize().y / 2, shape.getSize().x / 2, shape.getSize().y / 2);
        graphics.line(shape.getSize().x / 2, -shape.getSize().y / 2, -shape.getSize().x / 2, shape.getSize().y / 2);


        graphics.popMatrix();


    }
}
