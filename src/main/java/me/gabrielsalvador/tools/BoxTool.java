package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.event.KeyEvent;

public class BoxTool extends Tool{
    private Vec2 _initialPosition;
    private Vec2 _finalPosition;
    private CanvasController _canvas;
    private final ControlP5 _cp5;

    public BoxTool() {
        _cp5 = Sinesthesia.getInstance().getCP5();

    }
    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public void onPressed(PObject pObject,int[] mousePosition) {
        _initialPosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);

    }

    @Override
    public void onRelease(PObject pObject) {

    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {
        _finalPosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
    }

    @Override
    public void draw(PGraphics graphics) {
        if(_initialPosition != null && _finalPosition != null){
            graphics.pushStyle();
            graphics.noFill();
            graphics.stroke(255);
            graphics.rect(_initialPosition.x, _initialPosition.y, _finalPosition.x - _initialPosition.x, _finalPosition.y - _initialPosition.y);
            graphics.popStyle();
        }

    }

    public CanvasController getCanvas() {
        if (_canvas == null) {
            _canvas = (CanvasController) _cp5.get("MainCanvas");
        }
        return _canvas;
    }
}
