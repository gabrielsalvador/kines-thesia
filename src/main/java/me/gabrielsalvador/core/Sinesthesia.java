package me.gabrielsalvador.core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import controlP5.ControlFont;
import controlP5.ControlP5;
import controlP5.layout.LayoutBuilder;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.pobject.InspectorController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsBodyComponent;
import me.gabrielsalvador.pobject.PlayableNote;
import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.sequencing.Clock;
import me.gabrielsalvador.sequencing.SequencerController;
import me.gabrielsalvador.tools.ToolboxController;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PFont;

public class Sinesthesia extends PApplet {

    private static Sinesthesia _instance;
    private ControlP5 _cp5;
    private Clock _clock;

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
        System.setProperty("sun.java2d.uiScale.enabled", "false");
        PApplet.main("me.gabrielsalvador.core.Sinesthesia");
    }

    public void settings() {
        size(1280,820,P3D);
        registerMethod("dispose", this);
    }

    public void setup() {
        background(0);
        _cp5 = new ControlP5(this);
        _clock = Clock.getInstance();

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
//        PObject pObject = new PlayableNote();
//        PhysicsBodyComponent bodyComponent = new PhysicsBodyComponent(pObject,new Vec2(0,0));
//        pObject.addComponent(BodyComponent.class, bodyComponent);
//        AppController.getInstance().addPObject(pObject);
//
//        PObject pObject2 = new PlayableNote();
//        PhysicsBodyComponent bodyComponent2 = new PhysicsBodyComponent(pObject2,new Vec2(5,5));
//        pObject2.addComponent(BodyComponent.class, bodyComponent2);
//        AppController.getInstance().addPObject(pObject2);

//        PObject pObject3 = new PlayableNote();
//        PhysicsBodyComponent bodyComponent3 = new PhysicsBodyComponent(pObject3,new Vec2(100,100));
//        pObject3.addComponent(BodyComponent.class, bodyComponent3);
//        AppController.getInstance().addPObject(pObject3);

    }

    public void draw() {
        background(255);

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
