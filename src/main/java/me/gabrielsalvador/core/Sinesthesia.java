package me.gabrielsalvador.core;


import controlP5.ControlP5;
import me.gabrielsalvador.controllers.AppController;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject.PlayableNote;
import me.gabrielsalvador.ui.controllers.Canvas;
import me.gabrielsalvador.utils.Vector;
import processing.core.PApplet;

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
        size(800, 600);
    }

    public void setup() {
        background(0);
        _cp5 = new ControlP5(this);
        new Canvas(_cp5,"MainCanvas").setPosition(3, 6).setSize(500, 500);
        AppState appState = AppState.getInstance();
        PlayableNote note = new PlayableNote().setPosition(new Vector(100, 100));
        appState.addPObject(note);
        

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
