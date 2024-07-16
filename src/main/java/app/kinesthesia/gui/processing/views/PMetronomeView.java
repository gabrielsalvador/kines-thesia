package app.kinesthesia.gui.processing.views;


import app.kinesthesia.core.Component;
import app.kinesthesia.core.HologramBody;
import app.kinesthesia.gui.processing.components.body.HologramBodyView;

import processing.core.PGraphics;

public class PMetronomeView extends HologramBodyView implements View<Component> {

    private final BlinkingLigth _blinkingLigth;

    public PMetronomeView(HologramBody hologramBody) {
        super(hologramBody);
        _blinkingLigth = new BlinkingLigth(13);
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
