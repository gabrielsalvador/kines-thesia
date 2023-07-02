package me.gabrielsalvador.model.PObject;

import me.gabrielsalvador.ui.views.PlayableNoteView;
import me.gabrielsalvador.utils.Vector;

public class PlayableNote extends PObject{
    
    public PlayableNote() {
        super();
        setPosition(new Vector(0, 0));
        setSize((Defaults.DEFAULT_NOTE_SIZE));
        setView(new PlayableNoteView(this));
    }

    public PlayableNote setPosition(Vector position) {
        //convert Vector to float[]
        float[] pos = new float[3];
        pos[0] = position.getX();
        pos[1] = position.getY();
        super.setPosition(pos);
        return this;
    }

    public PlayableNote setSize(float size) {
        //convert Vector to float[]
        float[] s = new float[3];
        s[0] = size;
        super.setSize(s);
        return this;
    }
}
