package app.kinesthesia.gui.processing.tools;

import app.kinesthesia.core.Kinesthesia;
import app.kinesthesia.core.PObject;
import app.kinesthesia.gui.processing.ProcessingGuiMain;
import controlP5.ControlP5;
import app.kinesthesia.gui.CanvasController;
import app.kinesthesia.gui.processing.Config;
import app.kinesthesia.gui.processing.DisplayName;
import app.kinesthesia.gui.processing.PObjectPreset;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.event.KeyEvent;




@DisplayName("Resonator")
public class AddResonatorTool extends Tool {
    protected Vec2 _initialPosition;
    protected Vec2 _finalPosition;
    protected CanvasController _canvas;
    protected final ControlP5 _cp5;


    /* counts how many resonators have been added, useful for things like making it play several notes*/
    protected static int howManyResonators = 0;

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
        _cp5 = ProcessingGuiMain.getInstance().getCP5();

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

        Kinesthesia.getInstance().queueModification(() -> {

            PObjectPreset preset = new PObjectPreset.ResonatorPreset(initialPositionSnapshot, finalPositionSnapshot, _howManyResonators % 7);
            System.out.println("Creating resonator with scale note " + _howManyResonators % 7);
            PObject p = preset.create()[0];
            Kinesthesia.getInstance().addPObjectImmiadiately(p);
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
