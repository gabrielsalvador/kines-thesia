package me.gabrielsalvador.gui.processing.tools;


import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.DisplayName;
import me.gabrielsalvador.gui.processing.PKeyboard;
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