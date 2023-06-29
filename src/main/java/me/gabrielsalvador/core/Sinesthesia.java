package me.gabrielsalvador.core;

import processing.core.PApplet;


public class Sinesthesia extends PApplet{
    
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
    }

    public void draw() {
        background(255);

    }


    
}
