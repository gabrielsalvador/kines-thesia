package me.gabrielsalvador.views;

import processing.core.PGraphics;

public class BlinkingLigth {
    private final int size = 10;
    private final int MAX_BRIGHTNESS = 255;
    private int brigtness = 0;
    private int coolOffRate = 10;

    // Color values for the blinking light
    private int originalR = 255;  // red component (Set to red for this example)
    private int originalG = 0;    // green component
    private int originalB = 0;    // blue component

    public void display(PGraphics graphics) {

        graphics.colorMode(PGraphics.RGB); // Set color mode to RGB

        // Calculate current RGB values based on brightness
        int currentR = (int) (originalR * (brigtness / (float)MAX_BRIGHTNESS));
        int currentG = (int) (originalG * (brigtness / (float)MAX_BRIGHTNESS));
        int currentB = (int) (originalB * (brigtness / (float)MAX_BRIGHTNESS));

        // Use fill with RGB values
        graphics.fill(currentR, currentG, currentB);
        graphics.ellipse(0, 0, size, size);

        if (brigtness > coolOffRate) {
            brigtness -= coolOffRate;
        } else {
            brigtness = 0;
        }

    }

    public void blink() {
        System.out.println("blinking");
        brigtness = MAX_BRIGHTNESS;
    }
}
