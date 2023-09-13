package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.PlayableNote;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.BodyData;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import processing.core.PGraphics;
import processing.event.KeyEvent;

public class BoxTool extends Tool{
    private Vec2 _initialPosition;
    private Vec2 _finalPosition;
    private CanvasController _canvas;
    private ControlP5 _cp5;

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
    public void onPressed(PObject pObject) {
        _initialPosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);

    }

    @Override
    public void onRelease(PObject pObject) {
        _finalPosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
        PlayableNote box = new PlayableNote();
        Vec2 size = _finalPosition.sub(_initialPosition);
        BodyData bodyData = new BodyData();
        bodyData.x = _initialPosition.x;
        bodyData.y = _initialPosition.y;
        bodyData.bodyType = BodyType.DYNAMIC;
        bodyData.shapeType = ShapeType.POLYGON;
        bodyData.vertices = new Vec2[]{
                new Vec2(0, 0),
                new Vec2(size.x, 0),
                new Vec2(size.x, size.y),
                new Vec2(0, size.y)
        };
        PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(box, bodyData);
        box.addComponent(BodyComponent.class,bodyComponent);
        AppController.getInstance().addPObject(box);


    }

    @Override
    public void onDrag(PObject pObject) {

    }

    @Override
    public void draw(PGraphics graphics) {

    }

    public CanvasController getCanvas() {
        if (_canvas == null) {
            _canvas = (CanvasController) _cp5.get("MainCanvas");
        }
        return _canvas;
    }
}
