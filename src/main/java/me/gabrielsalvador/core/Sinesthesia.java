package me.gabrielsalvador.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import controlP5.ControlFont;
import controlP5.ControlP5;
import controlP5.layout.LayoutBuilder;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.controllers.AppController;
import me.gabrielsalvador.ui.controllers.Canvas;
import me.gabrielsalvador.ui.controllers.ToolboxController;
import processing.core.PApplet;
import processing.core.PFont;

public class Sinesthesia extends PApplet {

    private static Sinesthesia _instance;
    private ControlP5 _cp5;

    public Sinesthesia() {
        super();
        _instance = this;

    }

    public static synchronized Sinesthesia getInstance() {
        if (_instance == null) {
            _instance = new Sinesthesia();
        }

        return _instance;
    }

    public static void main(String[] args) {
        PApplet.main("me.gabrielsalvador.core.Sinesthesia");
    }

    public void settings() {
        size(1280,820,OPENGL);
    }

    public void setup() {
        background(0);
        _cp5 = new ControlP5(this);
        smooth();
        PFont   myFont = createFont("fonts/CascadiaCode_VTT.ttf", 12, true);
        ControlFont cfont = new ControlFont(myFont);
        _cp5.setFont(cfont);


        LayoutBuilder builder = new LayoutBuilder(this, _cp5);
        builder.addCustomClasses("Canvas", Canvas.class);
        builder.addCustomClasses("Toolbox", ToolboxController.class);

        try {
            Path xmlPath = Paths.get(Config.RESOURCES_PATH+"/mainLayout.xml");
            String xmlContent = new String(Files.readAllBytes(xmlPath));
            builder.parseXML(xmlContent);
        } catch (Exception e) {
            
            e.printStackTrace();
        }


    }

    public void draw() { 
        background(255);
    }

    public ControlP5 getCP5() {
        return _cp5;
    }

    public AppController getController() {
        return null;
    }

}
