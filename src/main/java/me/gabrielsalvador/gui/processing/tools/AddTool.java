package me.gabrielsalvador.gui.processing.tools;


import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.Config;
import me.gabrielsalvador.gui.processing.SkipProcessing;
import processing.core.PGraphics;
import processing.event.KeyEvent;

@SkipProcessing
public class AddTool extends Tool {

    {
        getModes().add(new ToolMode("Normal").setIcon(Config.ADDTOOL_CURSOR_ICON));

        setCurrentMode(getModes().get(0));
    }

    public AddTool() {

        
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {

    }


    @Override
    public void onClick(PObject pObject) {

        
    }

    @Override
    public boolean onPressed(PObject pObject,int[] mousePosition){
        return false;
    }




    @Override
    public void onRelease(PObject pObject) {

    }


    @Override
    public void onDrag(PObject pObject, int[] mousePosition) {

    }



    @Override
    public void draw(PGraphics graphics) {
        super.draw(graphics);
    }


}
