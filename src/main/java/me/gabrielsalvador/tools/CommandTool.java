package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.Textfield;
import controlP5.TextfieldCommand;
import me.gabrielsalvador.controllers.AppController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.ui.controllers.Canvas;
import me.gabrielsalvador.utils.Vector;
import processing.core.PApplet;

public class CommandTool extends Tool {

    private Textfield _textfield;

    public CommandTool() {
        ControlP5 cp5 = Sinesthesia.getInstance().getCP5();
        int x = cp5.getPointer().getX();
        int y = cp5.getPointer().getY();
        _textfield = (Textfield) cp5.getController("CommandTextfield");
        if (_textfield == null) {
            _textfield = cp5.addTextfield("CommandTextfield");
        }

        ((Controller<Textfield>) _textfield).setPosition(x, y).setActive();
        _textfield.setFocus(true);
        _textfield.show();
        _textfield.getKeyMapping().put((int) PApplet.ENTER, new EnterCommand());

    }

    @Override
    public void onEnter() {
        

    }

    @Override
    public void onLeave() {
        

    }

    @Override
    public void onRelease() {
        _textfield.clear();
        _textfield.hide();

    }

    @Override
    public void onReleaseOutside() {
        

    }

    @Override
    public void onPress() {

    }

    @Override
    public void onDrag() {

    }

    @Override
    public void onMove() {

    }

    @Override
    public void onClick(int x, int y) {
        
    }

    @Override
    public void onScroll() {

    }

    @Override
    public void onKey() {

    }

    class EnterCommand implements TextfieldCommand {

        @Override
        public void execute() {
            ControlP5 cp5 = Sinesthesia.getInstance().getCP5();
            Textfield tf = (Textfield) _textfield;
            String command = tf.getText();
            if (command.split(" ")[0].equals("add")) {
                String[] args = command.split(" ");
                int x = cp5.getPointer().getX();
                int y = cp5.getPointer().getY();
                AppController.getInstance().addPlayableNote(new Vector(x, y));
            }

            _textfield.clear();
            _textfield.hide();
            Canvas canvas = (Canvas) cp5.getController("MainCanvas");
            canvas.setActive();

        }

    }
}
