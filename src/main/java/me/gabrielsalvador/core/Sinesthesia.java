package me.gabrielsalvador.core;

import controlP5.Button;
import controlP5.MultilineTextfield;
import controlP5.Textfield;
import javafx.scene.text.Text;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject.PlayableNote;
import me.gabrielsalvador.ui.controllers.Canvas;
import me.gabrielsalvador.utils.Vector;
import processing.core.PApplet;

public class Sinesthesia extends PApplet {

    private static Sinesthesia _instance;
    private controlP5.ControlP5 cp5;

    public Sinesthesia() {
        super();

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
        cp5 = new controlP5.ControlP5(this);
        new Canvas(cp5,"").setPosition(3, 6).setSize(500, 500);
        cp5.saveLayout("layout.xml");
        AppState.getInstance().addPObject(new PlayableNote().setPosition(new Vector(100, 100)));
        Textfield myTextarea = new Textfield(cp5, "DXF").setPosition(30, 30);
        myTextarea.bringToFront();

    }

    public void draw() {
        background(255);
    }

}
