package me.gabrielsalvador.utils;

public class Color {
    public static int rgbToInt(int r, int g, int b) {
        return (255 << 24) | (r << 16) | (g << 8) | b;
    }
}
