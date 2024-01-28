package me.gabrielsalvador.pobject.components.musicalnote;


import controlP5.ControlP5;
import controlP5.Group;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.utils.MusicalNote;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public class MusicalNoteComponent extends Component {

    MusicalNote musicalNote;

    @PObject.InspectableProperty(displayName = "Note")
    public MusicalNote getMusicalNote() {
        return musicalNote;
    }

    @PObject.InspectableProperty.SetterFor("Note")
    public void setMusicalNote(MusicalNote musicalNote) {
        this.musicalNote = musicalNote;
    }


    @PObject.InspectableProperty(displayName = "Relative Pitch")
    public boolean getRelativePitch() {
        return false;
    }

    @PObject.InspectableProperty.SetterFor("Relative Pitch")
    public void setRelativePitch(boolean relativePitch) {

    }


    private PObject owner;

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

    public int getPitch() {
        return musicalNote.getPitch();
    }


    public static class MusicalNoteComponentUI extends Group {

        public MusicalNoteComponentUI(ControlP5 theControlP5, String theName) {
            super(theControlP5, theName);
        }
    }
}

