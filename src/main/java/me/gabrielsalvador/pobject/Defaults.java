package me.gabrielsalvador.pobject;

public class Defaults {
    public static final int[] DEFAULT_KEYBOARD_SIZE = new int[]{50,15};
    static float DEFAULT_NOTE_SIZE = 5;

    public static Object getDefaultValue(Class<?> type) {
        if (type == Integer.class) {
            return 0;
        } else if (type == Float.class) {
            return 0.0f;
        } else if (type == Boolean.class) {
            return false;
        } else if (type == String.class) {
            return "";
        } else if (type == Integer[].class) {
            return new Integer[0];
        } else if (type.getName().equals("[F")) {
            return new float[]{0.0f, 0.0f};
        } else if (type.getName().equals("[Ljava.lang.String;")) {
            return new String[0];
        } else if (type.getName().equals("[Ljava.lang.Integer;")) {
            return new Integer[0];
        } else if (type.getName().equals("[Ljava.lang.Float;")) {
            return new Float[0];
        } else if (type.getName().equals("[Z")) {
            return new boolean[0];
        } else if (type.getName().equals("[Ljava.lang.Boolean;")) {
            return new Boolean[0];
        } else {
            return null;
        }
    }
}
