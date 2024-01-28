package me.gabrielsalvador.core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;

import controlP5.*;
import controlP5.layout.LayoutBuilder;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.InspectorController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.sequencing.Clock;
import me.gabrielsalvador.sequencing.SequencerController;
import me.gabrielsalvador.tools.ToolboxController;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import processing.core.PApplet;
import processing.core.PFont;

public class Sinesthesia extends PApplet {

    private static Sinesthesia _instance;
    private InputManager _inputManager;
    private ControlP5 _cp5;
    private Clock _clock;

    public Sinesthesia() {
        super();
        _instance = this;

    }

    Textarea debugInfo;

    public static synchronized Sinesthesia getInstance() {
        if (_instance == null) {
            _instance = new Sinesthesia();
        }

        return _instance;
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale.enabled", "true");
        PApplet.main("me.gabrielsalvador.core.Sinesthesia");
    }

    public void settings() {
        size(1280,820);
        registerMethod("dispose", this);
    }

    public void setup() {
        background(0);
        _cp5 = new ControlP5(this);
        _clock = Clock.getInstance();
        _inputManager = InputManager.getInstance();

        smooth();
        PFont myFont = createFont("fonts/CascadiaCode_VTT.ttf", 12, true);
        ControlFont cfont = new ControlFont(myFont);
        _cp5.setFont(cfont);

        LayoutBuilder builder = new LayoutBuilder(this, _cp5);
        builder.addCustomClasses("Canvas", CanvasController.class);
        builder.addCustomClasses("Toolbox", ToolboxController.class);
        builder.addCustomClasses("Inspector", InspectorController.class);
        builder.addCustomClasses("Sequencer", SequencerController.class);

        try {
            Path xmlPath = Paths.get(Config.RESOURCES_PATH+"/mainLayout.xml");
            String xmlContent = new String(Files.readAllBytes(xmlPath));
            builder.parseXML(xmlContent);
        } catch (Exception e) {

            e.printStackTrace();
        }

        loadAppState();




        debugInfo = getCP5().addTextarea("debugInfo")
                .setPosition(700,600)
                .setSize(400, 200)
                .setColor(color(255, 0, 0))
                .setFont(createFont("arial", 20));



    }



    public void draw() {
        CanvasController canvas = (CanvasController) _cp5.getController("MainCanvas");
        int[] mousePosition = canvas.getMousePosition();
        background(255);
        StringBuilder debugText = new StringBuilder("FPS: " + frameRate + " \n ");
        debugText.append("Mouse: ").append(mousePosition[0]).append(", ").append(mousePosition[1]).append(" \n ");

        World world = PhysicsManager.getInstance().getWorld();

        int bodyCount = AppState.getInstance().getPObjects().size();
        debugText.append("PObjects: ").append(bodyCount).append(" \n ");



        debugInfo.setText(debugText.toString());
    }

    public ControlP5 getCP5() {
        return _cp5;
    }

    private void loadAppState() {

        try (FileInputStream fileIn = new FileInputStream("appState.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            AppState loadedState = (AppState) in.readObject();
            AppState myState = AppState.getInstance();
            myState.setCurrentTool(loadedState.getCurrentTool());
            for (PObject pObject : loadedState.getPObjects()) {
                myState.addPObject(pObject);
            }
            myState.loadSequencerState(loadedState.getSequencerState());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }
        public void dispose() {
        // Save the app state
        try (FileOutputStream fileOut = new FileOutputStream("appState.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(AppState.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //terminate app

        super.dispose();

    }

}
