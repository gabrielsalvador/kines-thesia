package me.gabrielsalvador.tools;



public abstract class Tool  {
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

    abstract public void onEnter();
    
    abstract public void onLeave();

    abstract public void onRelease();

    abstract public void onReleaseOutside();

    abstract public void onPress();

    abstract public void onDrag();

    abstract public void onMove();

    abstract public void onClick();

    abstract public void onScroll();

    abstract public void onKey();

}
