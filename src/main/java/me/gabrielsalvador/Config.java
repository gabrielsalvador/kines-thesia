package me.gabrielsalvador;

import me.gabrielsalvador.tools.AddTool;
import me.gabrielsalvador.tools.SelectTool;
import me.gabrielsalvador.tools.Tool;

import java.util.Map;

public class Config {
    public static final String RESOURCES_PATH = "src/main/resources";
    public static final String ICON_FOLDER_PATH = RESOURCES_PATH + "/icons";
    public static final Map<Class<? extends Tool>, String> toolIconNames = Map.of(
            SelectTool.class, "selectTool.png",
            AddTool.class,"addTool.png");




}