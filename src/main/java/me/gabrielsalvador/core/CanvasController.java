package me.gabrielsalvador.core;

import controlP5.*;
import controlP5.events.ReleasedOutsideListener;
import me.gabrielsalvador.tools.ToolManager;
import me.gabrielsalvador.views.CanvasView;
import processing.core.PGraphics;
import processing.event.KeyEvent;


// Custom controller class that extends Controller
public class CanvasController extends Controller<CanvasController> implements ReleasedOutsideListener {

    private final ToolManager _toolManager;

    public CanvasController(ControlP5 cp5, String name) {
        super(cp5, name);
        _myControllerView = new CanvasView();
        _toolManager = ToolManager.getInstance();
    }


    @Override
    public void onPress() {
        isActive = inside();
        setUserInteraction(isActive);
        // x and y are relative to the canvas
        int x = cp5.getPointer().getX() - (int) absolutePosition[0];
        int y = cp5.getPointer().getY() - (int) absolutePosition[1];
        _toolManager.getCurrentTool().onPressed(x, y);
    }

    @Override
    public void mousePressed() {
        super.mousePressed();
      Textfield t = (Textfield) cp5.get("CommandTextfield");
      if (t != null) {
        t.hide();
        t.clear();
      }

    }

    @Override
    public void mouseReleasedOutside() {
        isActive = false;
    }

    @Override
    public void onDrag() {

    }

    @Override
    public void onMove() {

    }

    @Override
    public void onClick() {

    }

    @Override
    public void onScroll(int theRotationValue) {

    }

    @Override
    public void keyEvent(KeyEvent theKeyEvent) {
        if (isUserInteraction && isActive && theKeyEvent.getAction() == KeyEvent.PRESS) {

            _toolManager.keyEvent(theKeyEvent);

        }

    }

    @Override
    public void draw(PGraphics graphics) {

        graphics.pushMatrix();
        graphics.translate(x(position), y(position));
        getView().display(graphics, this);
        graphics.popMatrix();

    }

}
