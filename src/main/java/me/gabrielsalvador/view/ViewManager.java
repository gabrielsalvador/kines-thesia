package me.gabrielsalvador.view;
import java.util.Set;

import me.gabrielsalvador.model.PObject;
import me.gabrielsalvador.model.AppState;

public class ViewManager {
    private static ViewManager _instance;
    

    private ViewManager() {}

    public static synchronized ViewManager getInstance() {
        if (_instance == null) {
            _instance = new ViewManager();
        }

        return _instance;
    }

    public void display() {
        Set<PObject> _pObjects = AppState.getInstance().getPObjects();
        for (PObject pObject : _pObjects) {
            PObjectView pObjectView = pObject.getView();
            pObjectView.display();
        }
    }
}
