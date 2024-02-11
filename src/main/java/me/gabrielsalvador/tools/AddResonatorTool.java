package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectPreset;
import me.gabrielsalvador.utils.Interval;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.event.KeyEvent;




@DisplayName("Resonator")
public class AddResonatorTool extends Tool {
    private Vec2 _initialPosition;
    private Vec2 _finalPosition;
    private CanvasController _canvas;
    private final ControlP5 _cp5;


    /* counts how many resonators have been added, useful for things like making it play several notes*/
    private static int howManyResonators = 0;

    {
        getModes().get(0).setIcon(Config.BOXTOOL_CURSOR_ICON);
        getModes().add(new ToolMode("SelectMultiple").setIcon(Config.BOXTOOL_CURSOR_ICON).setModifierKeys(KeyEvent.SHIFT));

        setCurrentMode(getModes().get(0));
    }

    {
        getModes().add(new ToolMode("Normal").setIcon(Config.BOXTOOL_CURSOR_ICON));

        setCurrentMode(getModes().get(0));
    }


    public AddResonatorTool() {
        _cp5 = Sinesthesia.getInstance().getCP5();

    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public boolean onPressed(PObject pObject, int[] mousePosition) {
        _initialPosition = new Vec2(getCanvas().getMousePosition()[0], getCanvas().getMousePosition()[1]);
        return false;
    }

    @Override
    public void onRelease(PObject pObject) {
        if (_initialPosition == null || _finalPosition == null) {
            return;
        }

        // Create snapshots of the current state inside the lambda
        Vec2 initialPositionSnapshot = new Vec2(_initialPosition.x, _initialPosition.y);
        Vec2 finalPositionSnapshot = new Vec2(_finalPosition.x, _finalPosition.y);
        int _howManyResonators = AddResonatorTool.howManyResonators;

        AppController.getInstance().queueModification(() -> {

            PObjectPreset preset = new PObjectPreset.ResonatorPreset(initialPositionSnapshot, finalPositionSnapshot, Interval.fromScaleDegree(_howManyResonators % 7));
            System.out.println("Creating resonator with scale note " + _howManyResonators % 7);
            PObject p = preset.create()[0];
            AppController.getInstance().addPObjectImmiadiately(p);
        });


        _initialPosition = null;
        _finalPosition = null;
        howManyResonators++;
    }


    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

        int[] mouse = getCanvas().getMousePosition();
        _finalPosition = new Vec2(mouse[0], mouse[1]);
    }


    @Override
    public void draw(PGraphics graphics) {
        super.draw(graphics);
        if (_initialPosition != null && _finalPosition != null) {
            int[] mouse = getCanvas().getMousePosition();
            graphics.pushStyle();
            graphics.stroke(255);
            graphics.strokeWeight(1);
            graphics.line(_initialPosition.x, _initialPosition.y, _finalPosition.x, _finalPosition.y);
            graphics.popStyle();

        }

    }

    public CanvasController getCanvas() {
        if (_canvas == null) {
            _canvas = (CanvasController) _cp5.get("MainCanvas");
        }
        return _canvas;
    }

    @Override
    public String getName() {
        return "Resonator";
    }
}
