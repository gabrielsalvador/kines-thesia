package me.gabrielsalvador.view;

public class ViewManager {
    private static ViewManager _instance;
    private ViewManager() {}

    public static synchronized ViewManager getInstance() {
        if (_instance == null) {
            _instance = new ViewManager();
        }

        return _instance;
    }
}
