package me.gabrielsalvador.gui.processing.tools;


import controlP5.ControlP5;
import controlP5.Textfield;
import controlP5.TextfieldCommand;
import me.gabrielsalvador.gui.processing.Main;
import me.gabrielsalvador.gui.CanvasController;
import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.SkipProcessing;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.lang.Kinescript;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.util.HashMap;

@SkipProcessing
public class CommandTool extends Tool {

    private Textfield _textfield;

    public CommandTool() {
        ControlP5 cp5 = Main.getInstance().getCP5();
        int x = cp5.getPointer().getX();
        int y = cp5.getPointer().getY();
        _textfield = (Textfield) cp5.getController("CommandTextfield");
        if (_textfield == null) {
            _textfield = cp5.addTextfield("CommandTextfield");
        }
        _textfield.show();
        _textfield.bringToFront();
        _textfield.setFocus(true);
        _textfield.skipNextEvent();
        _textfield.getKeyMapping().put((int) PApplet.ENTER, new EnterCommand());
        _textfield.setPosition(x, y - 40);
        _textfield.setActiveController();
        CanvasController canvas = (CanvasController) cp5.getController("MainCanvas");
        canvas.setUserInteraction(false);


    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

    }

    @Override
    public boolean onPressed(PObject pObject, int[] mousePosition) {
        return false;
    }


    @Override
    public void onRelease(PObject pObject) {

    }


    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }

    @Override
    public PImage getCursorIcon() {
        return null;
    }

    @Override
    public void draw(PGraphics graphics) {

    }


    public void setFocus(boolean focus) {
        _textfield.setFocus(focus);
    }


    class EnterCommand implements TextfieldCommand {

        @Override
        public void execute() {
            ControlP5 cp5 = Main.getInstance().getCP5();

            CanvasController canvas = (CanvasController) cp5.getController("MainCanvas");
            canvas.setUserInteraction(true);

            Textfield tf = _textfield;
            String command = tf.getText();

            KFunction program = Kinescript.compileFunction(command);

            try {
                program.execute(new HashMap<>());
            } catch (Exception e) {
                e.printStackTrace();
            }


            _textfield.clear();
            _textfield.hide();
            cp5.getWindow().setControllerActive(null);
        }
    }
}
