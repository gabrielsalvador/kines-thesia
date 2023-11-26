package me.gabrielsalvador.tools;

import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.KeyEvent;

@DisplayName("Routing")
public class RoutingTool extends Tool{

    private PObject _firstObject;

    {
        getModes().add(new ToolMode("Normal"));
        getModes().add(new ToolMode("DoingRouting"));
        setCurrentMode(getModes().get(0));
    }
    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {
    }

    @Override
    public void onPressed(PObject pObject, int[] mousePosition) {
        _firstObject = pObject;
        setCurrentMode(getModes().get(2));
        System.out.println(getCurrentMode().getName());
    }

    @Override
    public void onRelease(PObject pObject) {

    }

    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }

    @Override
    public void draw(PGraphics graphics) {
        if(getCurrentMode().getName().equals("DoingRouting")){
            PApplet papplet = Sinesthesia.getInstance();
            int[] mousePos = new int[]{papplet.mouseX, papplet.mouseY};
            graphics.stroke(255,0,0);
            graphics.strokeWeight(2);
            BodyComponent body = _firstObject.getComponent(BodyComponent.class);
            graphics.line(body.getPixelPosition().x, body.getPixelPosition().y, mousePos[0], mousePos[1]);

        }
    }



}
