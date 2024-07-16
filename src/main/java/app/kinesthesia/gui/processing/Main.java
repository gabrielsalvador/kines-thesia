package app.kinesthesia.gui.processing;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import app.kinesthesia.core.AppState;
import app.kinesthesia.core.Clock;
import app.kinesthesia.core.InputManager;
import app.kinesthesia.core.PObject;
import app.kinesthesia.gui.processing.ui.*;
import controlP5.*;
import controlP5.layout.LayoutBuilder;
import app.kinesthesia.gui.CanvasController;
import app.kinesthesia.kinescript.ast.KFunction;
import app.kinesthesia.kinescript.ast.KList;
import processing.core.PApplet;
import processing.core.PFont;

public class Main extends PApplet {

    private static Main _instance;
    private InputManager _inputManager;
    private ControlP5 _cp5;
    private Clock _clock;

    public Main() {
        super();
        _instance = this;
    }





    public static synchronized Main getInstance() {
        if (_instance == null) {
            _instance = new Main();

        }

        return _instance;
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale.enabled", "true");
        PApplet.main("app.kinesthesia.gui.processing.Main");
    }

    public void settings() {
        size(1280,820);
        registerMethod("dispose", this);
    }

    public void setup() {
        background(0);
        _cp5 = new ControlP5(this);
        _cp5.enableShortcuts();



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
        builder.addCustomClasses("Sequencer", SequencerGroup.class);
        builder.addCustomClasses("TransportButton", TransportButton.class);
        builder.addCustomClasses("Console", ConsoleGroup.class);

        try {
            Path xmlPath = Paths.get(Config.RESOURCES_PATH+"/mainLayout.xml");
            String xmlContent = new String(Files.readAllBytes(xmlPath));
            builder.parseXML(xmlContent);
        } catch (Exception e) {
            System.out.println("Error loading layout, check your mainLayout.xml file.");
            e.printStackTrace();
        }


        loadAppState();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                dispose();
            }
        });

        Map<String, Object> scope = KFunction.getScope();
        ArrayList<?> objects = AppState.getInstance().getPObjects();
        scope.put("all", new KList(objects));


        System.out.println("App initialized");
        System.out.println("=========================");
        System.out.println("Press SPACE to play/pause");
        System.out.println("Alt + / to toggle console");

    }



    public void draw() {
    }

    public ControlP5 getCP5() {
        return _cp5;
    }

    private void loadAppState() {

        try (FileInputStream fileIn = new FileInputStream("appState.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            AppState loadedState = (AppState) in.readObject();
            AppState myState = AppState.getInstance();
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

            // Wait for the clock to finish
            _clock.shutdown();
            try {
                if (!_clock.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Clock did not terminate in the given time.");
                    _clock.forceShutdown();
                }
            } catch (InterruptedException e) {
                System.err.println("Termination interrupted");
                _clock.forceShutdown();
            }

            super.dispose();

    }


}
