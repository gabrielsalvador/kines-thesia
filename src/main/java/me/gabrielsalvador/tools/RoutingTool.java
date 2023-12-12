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
import processing.core.PVector;
import processing.event.KeyEvent;

@DisplayName("Routing")
public class RoutingTool extends Tool{

    private static final int DOING_ROUTING = 1;
    private PObject _firstObject;




    {
        getModes().add(new ToolMode("DoingRouting"));
        setCurrentMode(getModes().get(MODE_NORMAL));
    }


    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {
    }

    @Override
    public void onPressed(PObject pObject, int[] mousePosition) {

        if(getCurrentMode().getName().equals("DoingRouting")){
            if(_firstObject == null || pObject == null) return;

            AppController.getInstance().createRouting(pObject, _firstObject);
            setCurrentMode(getModes().get(MODE_NORMAL));

        }else if (getCurrentMode().getName().equals("Normal")){
            _firstObject = pObject;
            setCurrentMode(getModes().get(DOING_ROUTING));
        }


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
            if (_firstObject == null) return;
            PApplet papplet = Sinesthesia.getInstance();
            int[] mousePos = AppController.getInstance().getCanvas().getMousePosition();
            graphics.stroke(255,0,0);
            graphics.strokeWeight(2);
            BodyComponent body = _firstObject.getComponent(BodyComponent.class);
            Vec2 center = body.getPixelCenter();
            graphics.line(center.x, center.y, mousePos[0], mousePos[1]);
        }
    }



}
