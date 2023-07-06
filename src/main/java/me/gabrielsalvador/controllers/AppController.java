package me.gabrielsalvador.controllers;

import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.model.PObject.PObject;
import me.gabrielsalvador.model.PObject.PlayableNote;
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
