package me.gabrielsalvador.gui.utils;

public class Color {
    public static int rgbToInt(int r, int g, int b) {
        return (255 << 24) | (r << 16) | (g << 8) | b;
    }

    public static int random() {
        return rgbToInt((int) Math.random() * 255, (int) Math.random() * 255, (int) Math.random() * 255);
    }
}
