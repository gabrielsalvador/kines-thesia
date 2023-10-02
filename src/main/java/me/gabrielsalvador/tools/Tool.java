package me.gabrielsalvador.tools;



import me.gabrielsalvador.pobject.PObject;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.io.Serializable;

public abstract class Tool implements Serializable {
    private String _name;
    private String _description;


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

    public abstract void onDrag(PObject pObject);

    public abstract void draw(PGraphics graphics);
}
