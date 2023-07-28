package me.gabrielsalvador.pobject;

import me.gabrielsalvador.utils.Vector;

import java.io.IOException;
import java.io.ObjectInputStream;


@Properties({
        @Property(name = "size", type = float[].class),
        @Property(name = "pitch", type = Integer.class),
        @Property(name = "attack", type = Float.class),
        @Property(name = "decay", type = Float.class),
        @Property(name = "sustain", type = Float.class),
        @Property(name = "release", type = Float.class)
})

public class PlayableNote extends PObject{


    public PlayableNote() {
        super();
        setPosition(new Vector(0, 0));
        setSize((Defaults.DEFAULT_NOTE_SIZE));
        setView(new PlayableNoteView(this));
    }


    public PlayableNote setPosition(Vector position) {
        //convert Vector to float[]
        float[] pos = new float[2];
        pos[0] = position.getX();
        pos[1] = position.getY();
        super.setPosition(pos);
        return this;
    }


    public PlayableNote setSize(float size) {
        //convert Vector to float[]
        float[] s = new float[2];
        s[0] = size;
        super.setSize(s);
        return this;
    }


    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PlayableNoteView(this));

    }
}
