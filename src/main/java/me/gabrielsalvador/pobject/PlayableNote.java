package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.views.PlayableNoteView;
import java.io.IOException;
import java.io.ObjectInputStream;


public class PlayableNote extends PObject{


    public PlayableNote() {
        super();
        initialize();
    }


    @Override
    protected void initialize() {

        setView(new PlayableNoteView(this));
    }

    @Override
    public void onEnter(int x, int y) {

    }

    @Override
    public void onLeave(int x, int y) {

    }





    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        initialize();

    }
}
