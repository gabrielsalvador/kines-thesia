package me.gabrielsalvador.tools;


import controlP5.ControlP5;
import controlP5.Controller;
import me.gabrielsalvador.core.Sinesthesia;

public class CommandTool  extends Tool{

    public CommandTool () {
        ControlP5 cp5 = Sinesthesia.getInstance().getCP5();
        int x = cp5.getPointer().getX();
        int y = cp5.getPointer().getY();
        Controller<?> controller = cp5.getController("CommandTextfield");
        if(controller == null ){
            controller = cp5.addTextfield("CommandTextfield");
        }
        controller.setPosition(x, y);
        
    }


    @Override
    public void onEnter() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onLeave() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onRelease() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onReleaseOutside() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPress() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDrag() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onMove() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onClick(int x, int y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onScroll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onKey() {
        // TODO Auto-generated method stub
        
    }
    
}
