package me.gabrielsalvador.pobject.views;

import processing.core.PGraphics;

public class BlinkingLigth {
    private final int _size;
    private final int MAX_BRIGHTNESS = 255;
    private final int DEFAULT_SIZE = 10;
    private int brightness = 0;
    private final int coolOffRate = 10;

    // Color values for the blinking light
    private final int originalR = 255;  // red component (Set to red for this example)
    private final int originalG = 0;    // green component
    private final int originalB = 0;    // blue component

    public BlinkingLigth() {
        _size = DEFAULT_SIZE;
    }
    public BlinkingLigth(int size) {
        _size = size;
    }

    public void display(PGraphics graphics) {

        graphics.colorMode(PGraphics.RGB);

        // Calculate current RGB values based on brightness
        int currentR = (int) (originalR * (brightness / (float)MAX_BRIGHTNESS));
        int currentG = (int) (originalG * (brightness / (float)MAX_BRIGHTNESS));
        int currentB = (int) (originalB * (brightness / (float)MAX_BRIGHTNESS));

        graphics.noStroke();
        graphics.fill(currentR, currentG, currentB,brightness);
        graphics.ellipse(0, 0, _size, _size);

        if (brightness > coolOffRate) {
            brightness -= coolOffRate;
        } else {
            brightness = 0;
        }

    }

    public void blink() {
        brightness = MAX_BRIGHTNESS;
    }
}
