package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.BodyData;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

public class BoxTool extends Tool{
    private Vec2 _initialPosition;
    private Vec2 _finalPosition;
    private CanvasController _canvas;
    private final ControlP5 _cp5;

    {
        getModes().add(new ToolMode("Normal").setIcon(Config.BOXTOOL_CURSOR_ICON));

        setCurrentMode(getModes().get(0));
    }


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
        if(_initialPosition == null || _finalPosition == null){
            return;
        }

        //add PObject to the canvas
        PObject pObject1 = new PObject();
        BodyData bodyData = new BodyData();
        bodyData.shapeType = ShapeType.POLYGON;
        bodyData.bodyType = BodyType.DYNAMIC;
        bodyData.vertices = new Vec2[4];
        bodyData.vertices[0] = new Vec2(0,0);
        bodyData.vertices[1] = new Vec2(_finalPosition.x - _initialPosition.x,0);
        bodyData.vertices[2] = new Vec2(_finalPosition.x - _initialPosition.x,_finalPosition.y - _initialPosition.y);
        bodyData.vertices[3] = new Vec2(0,_finalPosition.y - _initialPosition.y);

        PhysicsBodyComponent physicsBody = new PhysicsBodyComponent(pObject1,bodyData);
        physicsBody.setPixelPosition(new Vec2(_initialPosition.x,_initialPosition.y));
        pObject1.addComponent(BodyComponent.class,physicsBody);
        System.out.println("Body created at: " + physicsBody.getPosition().x + " " + physicsBody.getPosition().y);
        AppController.getInstance().addPObject(pObject1);

        _initialPosition = null;
        _finalPosition = null;

    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {
        _finalPosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
    }


    @Override
    public void draw(PGraphics graphics) {
        super.draw(graphics);
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
