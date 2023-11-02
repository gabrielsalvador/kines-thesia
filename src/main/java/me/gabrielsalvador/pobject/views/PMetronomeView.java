package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.components.body.HologramBodyView;
import processing.core.PGraphics;

public class PMetronomeView extends HologramBodyView implements View<Component> {

    private final BlinkingLigth _blinkingLigth;

    public PMetronomeView(HologramBody hologramBody) {
        super(hologramBody);
        _blinkingLigth = new BlinkingLigth(5);
    }

    @Override
    public void display(PGraphics graphics, Component model) {
        super.display(graphics, model);
        graphics.pushMatrix();
        graphics.pushStyle();
        graphics.ellipseMode(graphics.CENTER);
        graphics.translate(_model.getPixelPosition().x, _model.getPixelPosition().y);
        _blinkingLigth.display(graphics);
        graphics.popStyle();
        graphics.popMatrix();
    }

    public BlinkingLigth getBlinkingLight(){
        return _blinkingLigth;
    }
}
