package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import controlP5.Textfield;
import controlP5.TextfieldCommand;
import me.gabrielsalvador.controllers.AppController;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.ui.controllers.Canvas;
import me.gabrielsalvador.utils.Vector;
import processing.core.PApplet;
import processing.event.KeyEvent;

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
        _textfield.show();
        _textfield.setFocus(true);
        _textfield.skipNextEvent();
        _textfield.getKeyMapping().put((int) PApplet.ENTER, new EnterCommand());
        _textfield.setPosition(x, y - 40);
        Canvas canvas = (Canvas) cp5.getController("MainCanvas");
        canvas.setUserInteraction(false);


    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(int x, int y) {

    }


    public void setFocus(boolean focus) {
        _textfield.setFocus(focus);
    }

    class EnterCommand implements TextfieldCommand {

        @Override
        public void execute() {
            ControlP5 cp5 = Sinesthesia.getInstance().getCP5();

            Canvas canvas = (Canvas) cp5.getController("MainCanvas");
            canvas.setUserInteraction(true);

            Textfield tf = (Textfield) _textfield;
            String command = tf.getText();
            if (command.split(" ")[0].equals("add")) {
                String[] args = command.split(" ");
                int x = canvas.getPointer().x();
                int y = canvas.getPointer().y();
                AppController.getInstance().addPlayableNote(new Vector(x, y));
            }

            _textfield.clear();
            _textfield.hide();

        }

    }
}
