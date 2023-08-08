package me.gabrielsalvador;

import me.gabrielsalvador.tools.AddTool;
import me.gabrielsalvador.tools.SelectTool;
import me.gabrielsalvador.tools.Tool;
import me.gabrielsalvador.utils.Color;

import java.util.Map;

public interface Config {
    public static final String RESOURCES_PATH = "src/main/resources";
    public static final String ICON_FOLDER_PATH = RESOURCES_PATH + "/icons";
    public static final Map<Class<? extends Tool>, String> toolIconNames = Map.of(
            SelectTool.class, "selectTool.png",
            AddTool.class,"addTool.png");

    public String MAIN_SEQUENCER = "sequencer";

    public static final int THEME_COLOR_SELECTED = Color.rgbToInt(100,217,244);
    public static final int THEME_COLOR_ROUTING_CONNECTION = Color.rgbToInt(95,207,249);
    float PHYSICS_NOTE_DEFAULT_SIZE = 10;


    public static class Shortcuts {
        public static final char CLONE = 'c';
        // Add more shortcuts here if needed
    }
}
