package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import controlP5.Textfield;
import controlP5.TextfieldCommand;
import me.gabrielsalvador.common.SkipProcessing;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.pobject.*;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.KeyEvent;

@SkipProcessing
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
        _textfield.bringToFront();
        _textfield.setFocus(true);
        _textfield.skipNextEvent();
        _textfield.getKeyMapping().put((int) PApplet.ENTER, new EnterCommand());
        _textfield.setPosition(x, y - 40);
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
    public void onPressed(PObject pObject) {

    }



    @Override
    public void onRelease(PObject pObject) {

    }


    @Override
    public void onDrag(PObject pObject) {

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
            ControlP5 cp5 = Sinesthesia.getInstance().getCP5();

            CanvasController canvas = (CanvasController) cp5.getController("MainCanvas");
            canvas.setUserInteraction(true);

            Textfield tf = _textfield;
            String command = tf.getText();
            String[] split = command.split(" ");
            if (split[0].equals("add")) {
                String[] args = command.split(" ");
                int x = canvas.getPointer().x();
                int y = canvas.getPointer().y();
                if(args[1].equals("keyboard")){

                }else if(args[1].equals("emitter")) {

                }else if(args[1].equals("block")) {

                }
                else if (args[1].equals("esystem")){

                }

                else {

                }
            }else if(split[0].equals("clear")) {
                AppController.getInstance().queueModification(() -> {
                    AppState.getInstance().clearObjects();
                });
            }

            _textfield.clear();
            _textfield.hide();
        }
    }
}
