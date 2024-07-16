package app.kinesthesia.core;

import org.jbox2d.common.Vec2;

public class MathUtils {
    public static float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static boolean isInsideRectangle(Vec2 position, Vec2 selectionStart, Vec2 selectionEnd) {
        float upperLeftX = Math.min(selectionStart.x, selectionEnd.x);
        float upperLeftY = Math.min(selectionStart.y, selectionEnd.y);
        float lowerRightX = Math.max(selectionStart.x, selectionEnd.x);
        float lowerRightY = Math.max(selectionStart.y, selectionEnd.y);

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

    private static float dist(float px, float py, float x1, float y1) {
        return (float) Math.sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
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

    public static Vec2 rotateVector(Vec2 v, float angle) {
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);

        float xnew = v.x * c - v.y * s;
        float ynew = v.x * s + v.y * c;

        return new Vec2(xnew, ynew);
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

    public static int noteLetterToPitch(String noteName){

        if(!noteName.matches("([A-G])(#|b)?")){
            throw new IllegalArgumentException("Notes can only be from A to G and can have a # or b after it.");
        }
        return switch (noteName) {

            case "Cb" -> 11;
            case "C" -> 12;
            case "C#", "Db" -> 13;
            case "D" -> 14;
            case "D#", "Eb" -> 15;
            case "E", "Fb" -> 16;
            case "F", "E#" -> 17;
            case "F#", "Gb" -> 18;
            case "G" -> 19;
            case "G#", "Ab" -> 20;
            case "A" -> 21;
            case "A#", "Bb" -> 22;
            case "B" -> 23;


            default -> throw new IllegalArgumentException("Notes can only be from A to G and can have a # or b after it.");

        };
    }

    public static String pitchToNoteLetter(int pitch) {
        int note = pitch % 12;
        return switch (note) {
            case 0 -> "C";
            case 1 -> "C#";
            case 2 -> "D";
            case 3 -> "D#";
            case 4 -> "E";
            case 5 -> "F";
            case 6 -> "F#";
            case 7 -> "G";
            case 8 -> "G#";
            case 9 -> "A";
            case 10 -> "A#";
            case 11 -> "B";
            default -> throw new IllegalArgumentException("Invalid pitch");
        };
    }

    public static boolean isPointOverLineSegment(int pointX, int pointY, float x1, float y1, float x2, float y2, float margin) {
        float A = y2 - y1;
        float B = x1 - x2;
        float C = x2*y1 - x1*y2;

        float distance = Math.abs(A*pointX + B*pointY + C) / (float)Math.sqrt(A*A + B*B);

        boolean withinX = (pointX >= Math.min(x1, x2) - margin) && (pointX <= Math.max(x1, x2) + margin);
        boolean withinY = (pointY >= Math.min(y1, y2) - margin) && (pointY <= Math.max(y1, y2) + margin);

        return distance <= margin && withinX && withinY;
    }

    public static Vec2 coordWorldToPixels(float x, float y) {
        // sample implementation
        return new Vec2(x * 30, y * 30);
    }
    public static Vec2 coordWorldToPixels(Vec2 v) {
        return coordWorldToPixels(v.x, v.y);
    }

    public static Vec2 coordPixelsToWorld(float x, float y) {
        // sample implementation
        return new Vec2(x / 30, y / 30);
    }
}
