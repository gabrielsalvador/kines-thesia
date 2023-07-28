package me.gabrielsalvador.pobject;

import me.gabrielsalvador.views.View;
import processing.core.PGraphics;

public class PObjectView implements View<PObject> {

    private PObject _model;

    public PObjectView(PObject model) {
        _model = model;
    }

    @Override
    public PObject getModel() {
        return _model;
    }

    @Override
    public void display(PGraphics graphics) {

        for (String subObject : _model.getSubObjects().keySet()) {
            View view = _model.getSubObjects().get(subObject).getView();
            if (view != null) view.display(graphics);
        }
    }

    @Override
    public boolean isMouseOver(int mouseX, int mouseY) {
        return false;
    }
}
