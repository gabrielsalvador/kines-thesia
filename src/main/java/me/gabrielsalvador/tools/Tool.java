package me.gabrielsalvador.tools;




import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.io.Serializable;

public abstract class Tool implements Serializable {
    private String _name;
    private String _description;

    private final PApplet _papplet = Sinesthesia.getInstance();


    public Tool() {

    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }
    
    public void setDescription(String description) {
        _description = description;
    }

    public abstract void keyEvent(KeyEvent keyEvent);

    public abstract void onClick(PObject pObject);

    public abstract void onPressed(PObject pObject,int[] mousePosition);
    public abstract void onRelease(PObject pObject) ;

    public abstract void onDrag(PObject pObject, int[] mousePosition);

    public abstract PImage getCursorIcon();
    public void draw(PGraphics graphics){

        PImage cursorIcon = getCursorIcon();
        if(cursorIcon != null){
            _papplet.cursor(cursorIcon, 0,0);
        }
    }
}
