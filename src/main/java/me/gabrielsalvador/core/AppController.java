package me.gabrielsalvador.core;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PlayableNote;
import me.gabrielsalvador.utils.Vector;



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

    public PlayableNote addPlayableNote(Vector position) {
        PlayableNote note = new PlayableNote().setPosition(position);
        AppState.getInstance().addPObject(note);
        return note;
    }
        
     

   
}
