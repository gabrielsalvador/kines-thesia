package me.gabrielsalvador.tools;



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

    public abstract void onClick(int x, int y) ;


    public abstract void onPressed(int x, int y);
    public abstract void onRelease(int x, int y) ;

    // Add this method to handle mouse drag events
    public abstract void onDrag(int x, int y);
}
