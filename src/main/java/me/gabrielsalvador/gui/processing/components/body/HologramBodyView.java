package me.gabrielsalvador.gui.processing.components.body;


import me.gabrielsalvador.core.Component;
import me.gabrielsalvador.core.HologramBody;
import me.gabrielsalvador.gui.processing.views.View;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;


public class HologramBodyView implements View<Component> {
    protected final HologramBody _model;

    public HologramBodyView(HologramBody hologramBody) {
        _model = hologramBody;

    }

    @Override
    public Component getModel() {
        return _model;
    }

    @Override
    public void display(PGraphics graphics,Component model) {
        
        graphics.pushStyle();
        graphics.pushMatrix();

        Vec2 pixelPosition = _model.getPixelPosition();
        graphics.translate(pixelPosition.x, pixelPosition.y);

//        _model.getShape().display(graphics);
        graphics.popMatrix();
        graphics.popStyle();


    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        return _model.getShape().isMouseOver(mouseX, mouseY, _model.getPixelPosition().x, _model.getPixelPosition().y);
    }
}
