package me.gabrielsalvador.utils;

import org.jbox2d.common.Vec2;

public class MathUtils {
    public static float distance(float x1, float y1, float x2, float y2) {
        return (float) java.lang.Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static boolean isInsideRectangle(Vec2 position, Vec2 selectionStart, Vec2 selectionEnd) {
        float upperLeftX = java.lang.Math.min(selectionStart.x, selectionEnd.x);
        float upperLeftY = java.lang.Math.min(selectionStart.y, selectionEnd.y);
        float lowerRightX = java.lang.Math.max(selectionStart.x, selectionEnd.x);
        float lowerRightY = java.lang.Math.max(selectionStart.y, selectionEnd.y);

        if(position.x >= upperLeftX && position.x <= lowerRightX && position.y >= upperLeftY && position.y <= lowerRightY) {
            return true;
        }
        return false;
    }
}
