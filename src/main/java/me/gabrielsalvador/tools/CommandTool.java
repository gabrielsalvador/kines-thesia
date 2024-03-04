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
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.util.ArrayList;

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
                if (args[1].equals("keyboard")) {
                    AppController app = AppController.getInstance();
                    PObject p = new PObjectPreset.KeyboardPreset(new Vec2(x, y)).create()[0];
                    app.addPObject(p);


                } else if (args[1].equals("emitter")) {
                    AppController app = AppController.getInstance();
                    PObject p = new PObjectPreset.EmitterPreset(new Vec2(x, y)).create()[0];
                    app.addPObject(p);


                } else if (args[1].equals("block")) {

                } else if (args[1].equals("esystem")) {

                } else {

                }
            } else if (split[0].equals("clear")) {
                AppController.getInstance().queueModification(() -> {
                    AppState.getInstance().clearObjects();
                });
            } else if (split[0].equals("clearmoving")) {
                AppController.getInstance().queueModification(() -> {

                    ArrayList<PObject> _pObjects = AppState.getInstance().getPObjects();

                    for (int i = _pObjects.size() - 1; i >= 0; i--) {
                        //if bodycomponent shape is a sphere
                        BodyComponent body = _pObjects.get(i).getComponent(BodyComponent.class);
                        if (body instanceof PhysicsBodyComponent physicsBody) {
                            if (physicsBody.getJBox2DBody().getLinearVelocity().length() > 0) {
                                _pObjects.get(i).remove();
                                _pObjects.remove(i);
                            }
                        }
                    }

                });
            }

            _textfield.clear();
            _textfield.hide();
        }
    }
}
