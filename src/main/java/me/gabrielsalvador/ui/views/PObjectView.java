package me.gabrielsalvador.ui.views;

import me.gabrielsalvador.utils.Vector;
import processing.core.PGraphics;
import me.gabrielsalvador.model.PObject;

public class PObjectView implements ViewInterface<PObject>{
    
    private PObject _model;
    
    public PObjectView(PObject model) {
        _model = model;
    }
    
    public PObjectView addModel(PObject model) {
        _model = model;
        return null;
    }
    
    public PObject getModel() {
        return _model;
    }

    public void display(PGraphics graphics) {
        Vector position = _model.getPosition();

        //draw a ball at the position
        graphics.pushStyle();
        graphics.strokeWeight(1);
        graphics.stroke(0);
        graphics.fill(255,255,255);
        graphics.ellipse(position.getX(), position.getY(), 10, 10);
        graphics.popStyle();
        
    }
    
}