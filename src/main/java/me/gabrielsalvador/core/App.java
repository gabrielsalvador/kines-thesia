package me.gabrielsalvador.core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import controlP5.*;
import controlP5.layout.LayoutBuilder;
import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import me.friwi.jcefmaven.UnsupportedPlatformException;
import me.friwi.jcefmaven.impl.progress.ConsoleProgressHandler;
import me.gabrielsalvador.Config;
import me.gabrielsalvador.kinescript.ast.KFunction;
import me.gabrielsalvador.kinescript.ast.KList;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.ui.*;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.timing.Clock;
import org.cef.CefApp;
import org.cef.browser.CefBrowser;
import processing.core.PApplet;
import processing.core.PFont;

import javax.swing.*;

public class App  {

    private static App _instance;

    public App() throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {
        super();
        _instance = this;


        CanvasController canvasController = new CanvasController();
        AppController.getInstance().addDroplet();
        Clock.getInstance().play();




    }


    public static synchronized App getInstance() throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {
        if (_instance == null) {
            _instance = new App();

        }

        return _instance;
    }




    public void setup() {



        loadAppState();


        Map<String, Object> scope = KFunction.getScope();
        ArrayList<?> objects = AppState.getInstance().getPObjects();
        scope.put("all", new KList(objects));


        System.out.println("App initialized");
        System.out.println("=========================");
        System.out.println("Press SPACE to play/pause");
        System.out.println("Alt + / to toggle console");

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


    public static void main(String[] args) throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException {
        App app = App.getInstance();

    }



}
