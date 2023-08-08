package me.gabrielsalvador.pobject.views;

import processing.core.PGraphics;

public class BlinkingLigth {
    private int _size;
    private final int MAX_BRIGHTNESS = 255;
    private int brigtness = 0;
    private int coolOffRate = 10;

    // Color values for the blinking light
    private int originalR = 255;  // red component (Set to red for this example)
    private int originalG = 0;    // green component
    private int originalB = 0;    // blue component

    public BlinkingLigth(int size) {
        _size = size;
    }

    public void display(PGraphics graphics) {

        graphics.colorMode(PGraphics.RGB);

        // Calculate current RGB values based on brightness
        int currentR = (int) (originalR * (brigtness / (float)MAX_BRIGHTNESS));
        int currentG = (int) (originalG * (brigtness / (float)MAX_BRIGHTNESS));
        int currentB = (int) (originalB * (brigtness / (float)MAX_BRIGHTNESS));

        graphics.noStroke();
        graphics.fill(currentR, currentG, currentB);
        graphics.ellipse(0, 0, _size, _size);

        if (brigtness > coolOffRate) {
            brigtness -= coolOffRate;
        } else {
            brigtness = 0;
        }

    }

    public void blink() {
        brigtness = MAX_BRIGHTNESS;
    }
}
