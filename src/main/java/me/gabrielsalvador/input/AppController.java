package me.gabrielsalvador.input;

import me.gabrielsalvador.model.PObject;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.view.ViewManager; 

public class AppController {
    private static AppController _instance;
    

    private AppController() {

    }

    public static synchronized AppController getInstance() {
        if (_instance == null) {
            _instance = new AppController();
        }

        return _instance;
    }

    public void addPObject(PObject pObject) {
        AppState.getInstance().addPObject(pObject);
    }
        
        
    
    public void render(){
        ViewManager.getInstance().display();
    }

   
}
