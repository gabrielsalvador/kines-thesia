package me.gabrielsalvador.model;

import me.gabrielsalvador.tool.Tool;

public class State {
    private static State _instance;
    private Tool currentTool;


    private State() {}

    public static synchronized State getInstance() {
        if (_instance == null) {
            _instance = new State();
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
