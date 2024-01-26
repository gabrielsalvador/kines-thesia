package me.gabrielsalvador.pobject.components.musicalnote;

import controlP5.*;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.views.componentui.MusicalNoteUI;
import me.gabrielsalvador.utils.MusicalNote;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class MusicalNoteComponent extends Component {

    @PObject.InspectableProperty(displayName = "Note")
    MusicalNote musicalNote;

    private  PObject owner;
    public MusicalNoteComponent(PObject owner, int pitch) {
        super(owner);
        this.owner = owner;
        musicalNote = new MusicalNote(pitch);

    }

    @Override
    public void display(PGraphics graphics) {
        Vec2 center = owner.getBodyComponent().getPixelCenter();
        graphics.text(musicalNote.getFullName(), center.x, center.y);
    }

    @Override
    public String getName() {
        return "Musical Note";
    }

    @Override
    public void remove() {

    }

    @Override
    public MusicalNoteUI getUI(){
        return new MusicalNoteUI(this);
    }


    public int getPitch() {
        return musicalNote.getPitch();
    }
}

