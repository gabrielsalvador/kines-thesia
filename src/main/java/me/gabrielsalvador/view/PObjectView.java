package me.gabrielsalvador.view;

import me.gabrielsalvador.model.PObject;

public class PObjectView implements View<PObject>{
    
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

    public void display() {
        
    }
    
}