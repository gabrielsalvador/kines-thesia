package me.gabrielsalvador.pobject;

import me.gabrielsalvador.utils.Vector;

import java.util.HashMap;
import java.util.Map;

public class PlayableNote extends PObject{



    @Property(name = "pitch", type = Integer.class)
    protected int _pitch = 0;
    @Property(name = "velocity", type = Integer.class)
    protected int _velocity = 0;
    @Property(name = "attack", type = Float.class)
    protected float attack = 1;
    @Property(name = "decay", type = Float.class)
    protected float decay = 1;
    @Property(name = "sustain", type = Float.class)
    protected float sustain = 1;
    @Property(name = "release", type = Float.class)
    protected float release = 1;



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
