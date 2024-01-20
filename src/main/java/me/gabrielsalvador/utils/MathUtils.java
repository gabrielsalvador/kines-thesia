package me.gabrielsalvador.utils;

import org.jbox2d.common.Vec2;

import static processing.core.PApplet.dist;

public class MathUtils {
    public static float distance(float x1, float y1, float x2, float y2) {
        return (float) java.lang.Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static boolean isInsideRectangle(Vec2 position, Vec2 selectionStart, Vec2 selectionEnd) {
        float upperLeftX = java.lang.Math.min(selectionStart.x, selectionEnd.x);
        float upperLeftY = java.lang.Math.min(selectionStart.y, selectionEnd.y);
        float lowerRightX = java.lang.Math.max(selectionStart.x, selectionEnd.x);
        float lowerRightY = java.lang.Math.max(selectionStart.y, selectionEnd.y);

        return position.x >= upperLeftX && position.x <= lowerRightX && position.y >= upperLeftY && position.y <= lowerRightY;
    }

    public static boolean isPointOnLine(float x1, float y1, float x2, float y2, float px, float py) {
        // get distance from the point to the two ends of the line
        float d1 = dist(px,py, x1,y1);
        float d2 = dist(px,py, x2,y2);

        // get the length of the line
        float lineLen = dist(x1,y1, x2,y2);

        // since floats are so minutely accurate, add
        // a little buffer zone that will give collision
        float buffer = 0.1f;    // higher # = less accurate

        // if the two distances are equal to the line's
        // length, the point is on the line!
        // note we use the buffer here to give a range,
        // rather than one #
        return d1 + d2 >= lineLen - buffer && d1 + d2 <= lineLen + buffer;
    }

    public static  Vec2 rotatePoint(float cx, float cy, float angle, Vec2 p) {
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);

        // translate point back to origin:
        p = p.sub(new Vec2(cx, cy));

        // rotate point
        float xnew = p.x * c - p.y * s;
        float ynew = p.x * s + p.y * c;

        // translate point back:
        return new Vec2(xnew + cx, ynew + cy);
    }

    public static float calculateAngle(Vec2 v1, Vec2 v2) {
        float dotProduct = Vec2.dot(v1, v2);
        float magnitudeProduct = v1.length() * v2.length();
        float angleInRadians = (float) Math.acos(dotProduct / magnitudeProduct);


        float crossProduct = v1.x * v2.y - v1.y * v2.x;


        if (crossProduct < 0) {
            angleInRadians = -angleInRadians;
        }

        return (float) -Math.toDegrees(angleInRadians);
    }
}
