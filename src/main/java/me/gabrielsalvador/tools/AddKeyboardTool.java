package me.gabrielsalvador.tools;

import controlP5.ControlP5;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.common.DisplayName;
import me.gabrielsalvador.core.App;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.pobject.PKeyboard;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectPreset;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;
import processing.event.KeyEvent;


@DisplayName("Keyboard")
public class AddKeyboardTool extends Tool {


    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }

    @Override
    public void onClick(PObject pObject) {

        PKeyboard keyboard = new PKeyboard();
        keyboard.getBodyComponent().setPixelPosition(new Vec2(50,50));
        AppController.getInstance().addPObjectImmiadiately(keyboard);
    }
}