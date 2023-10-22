package me.gabrielsalvador.tools;

import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Tool implements Serializable {
    private String _name;
    private String _description;

    private final PApplet _papplet = Sinesthesia.getInstance();

    private ArrayList<ToolMode> _modes = new ArrayList<>();
    private ToolMode _currentMode = null;

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

    public PImage getCursorIcon(){
        if(_currentMode != null){
            return _currentMode.getCursorIcon();
        }
        return null;

    }
    public void draw(PGraphics graphics){

        
        switchMode();// running this in the draw loop is makes the modes set in a latch way, for now its ok but later lets handle this in KeyEvents
        PImage cursorIcon = getCursorIcon();
        if(cursorIcon != null){
            _papplet.cursor(cursorIcon, 0,0);
        }
    }

    private void switchMode(){
        setCurrentMode(getModes().get(0));
        for(ToolMode mode : getModes()){
            if(mode.shouldSwitchMode(Sinesthesia.getInstance())){
                setCurrentMode(mode);
                break;
            }
        }
    }

    public ArrayList<ToolMode> getModes() {
        return _modes;
    }
    public Tool setCurrentMode(ToolMode mode){
        _currentMode = mode;
        return this;
    }
    public ToolMode getCurrentMode(){
        return _currentMode;
    }
    
}
