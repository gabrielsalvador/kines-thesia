package me.gabrielsalvador.tools;

public class ToolManager {
    private static ToolManager _instance;
    private Tool currentTool = new SelectTool();

    private ToolManager() {}

    public static synchronized ToolManager getInstance() {
        if (_instance == null) {
            _instance = new ToolManager();
        }

        return _instance;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }
    
    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }
}
