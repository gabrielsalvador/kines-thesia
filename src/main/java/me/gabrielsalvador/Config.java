package me.gabrielsalvador;

import me.gabrielsalvador.tools.AddTool;
import me.gabrielsalvador.tools.SelectTool;
import me.gabrielsalvador.tools.Tool;
import me.gabrielsalvador.utils.Color;

import java.util.Map;

public interface Config {
    String RESOURCES_PATH = "src/main/resources";
    String ICON_FOLDER_PATH = RESOURCES_PATH + "/icons/";
    Map<Class<? extends Tool>, String> toolIconNames = Map.of(
            SelectTool.class, "selectTool.png",
            AddTool.class,"addTool.png");

    String MAIN_SEQUENCER = "sequencer";

    int THEME_COLOR_SELECTED = Color.rgbToInt(255,123,244);
    int THEME_COLOR_ROUTING_CONNECTION = Color.rgbToInt(95,207,249);
    float PHYSICS_NOTE_DEFAULT_SIZE = 3;


    String SELECTTOOL_CURSOR_ARROW_ICON = "selectNormal.png";
    String SELECTTOOL_CURSOR_ADD_ICON = "selectAdd.png";
    String ADDTOOL_CURSOR_ICON = "addCursor.png";
    String BOXTOOL_CURSOR_ICON = "boxCursor.png";

    class Shortcuts {
        public static final char CLONE = 'c';
        // Add more shortcuts here if needed
    }
}
