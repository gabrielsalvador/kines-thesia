package me.gabrielsalvador.pobject;

import me.gabrielsalvador.pobject.components.BodyComponent;
import me.gabrielsalvador.pobject.views.PlayableNoteView;
import me.gabrielsalvador.utils.Vector;
import org.jbox2d.common.Vec2;

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
