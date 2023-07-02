package me.gabrielsalvador.utils;

public class MathUtils {
    public static float distance(float x1, float y1, float x2, float y2) {
        return (float) java.lang.Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}
