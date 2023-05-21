package me.gabrielsalvador.tools;

public abstract class Tool {
    private String _name;
    private String _description;
    private ToolController controller;

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
    public ToolController getController() {
        return controller;
    }
    public void setController(ToolController controller) {
        this.controller = controller;
    }
}
