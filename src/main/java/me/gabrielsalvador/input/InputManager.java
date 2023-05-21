package me.gabrielsalvador.input;

public class InputManager {
    private static InputManager _instance;
    private InputManager() {}

    public static synchronized InputManager getInstance() {
        if (_instance == null) {
            _instance = new InputManager();
        }

        return _instance;
    }
}
