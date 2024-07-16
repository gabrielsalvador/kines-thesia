package app.kinesthesia.gui.processing;


import app.kinesthesia.gui.processing.tools.AddTool;
import app.kinesthesia.gui.processing.tools.SelectTool;
import app.kinesthesia.gui.processing.tools.Tool;
import app.kinesthesia.gui.processing.utils.Color;

import java.util.Map;

public interface Config {
    String RESOURCES_PATH = "src/main/resources";
    String ICON_FOLDER_PATH = RESOURCES_PATH + "/icons/";
    Map<Class<? extends Tool>, String> toolIconNames = Map.of(
            SelectTool.class, "selectTool.png",
            AddTool.class,"addTool.png");

    String MAIN_SEQUENCER_NAME = "sequencer";

    int THEME_COLOR_SELECTED = Color.rgbToInt(255,123,244);
    int THEME_COLOR_ROUTING_CONNECTION = Color.rgbToInt(95,207,249);
    float PHYSICS_NOTE_DEFAULT_SIZE = 3;


    String SELECTTOOL_CURSOR_ARROW_ICON = "selectNormal.png";
    String SELECTTOOL_CURSOR_ADD_ICON = "selectAdd.png";
    String ADDTOOL_CURSOR_ICON = "addCursor.png";
    String BOXTOOL_CURSOR_ICON = "boxCursor.png";
    String PIANO_ROLL_GROUP_NAME = "piano-roll";

    int[] APP_BACKGROUND_COLOR = {30,30,30};

    class Shortcuts {
        public static final char CLONE = 'c';
        // Add more shortcuts here if needed
    }
}
