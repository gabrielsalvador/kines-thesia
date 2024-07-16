package app.kinesthesia.gui.processing.tools;


import app.kinesthesia.core.AppController;
import app.kinesthesia.core.PObject;
import app.kinesthesia.gui.processing.PKeyboard;
import app.kinesthesia.gui.processing.DisplayName;
import org.jbox2d.common.Vec2;
import processing.event.KeyEvent;


@DisplayName("Keyboard")
public class AddKeyboardTool extends Tool {


    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

        PKeyboard keyboard = new PKeyboard();
        keyboard.getBodyComponent().setPosition(new Vec2(50,50));
        AppController.getInstance().addPObjectImmiadiately(keyboard);
    }
}