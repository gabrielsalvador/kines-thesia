package me.gabrielsalvador.pobject.components.musicalnote;


import controlP5.ControlP5;
import controlP5.Group;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.utils.Interval;
import org.jbox2d.common.Vec2;
import processing.core.PConstants;
import processing.core.PGraphics;

public class MusicalNoteComponent extends Component {

    Interval interval;

    @PObject.InspectableProperty(displayName = "Note")
    public Interval getInterval() {
        return interval;
    }

    @PObject.InspectableProperty.SetterFor("Note")
    public void setInterval(Interval interval) {
        this.interval = interval;
    }


    @PObject.InspectableProperty(displayName = "Relative Pitch")
    public boolean getRelativePitch() {
        return false;
    }

    @PObject.InspectableProperty.SetterFor("Relative Pitch")
    public void setRelativePitch(boolean relativePitch) {

    }


    private final PObject owner;

    public MusicalNoteComponent(PObject owner, Interval note) {
        super(owner);
        this.owner = owner;
        interval = note;

    }

    @Override
    public void display(PGraphics graphics) {
        String noteName = interval.getName();
        graphics.textSize(10);
        graphics.textAlign(PConstants.CENTER, PConstants.CENTER);

        Vec2 center = owner.getBodyComponent().getPixelCenter();
        graphics.fill(0);
        graphics.text(noteName, center.x, center.y);
    }

    @Override
    public String getName() {
        return "Musical Note";
    }

    @Override
    public void remove() {

    }




    public static class MusicalNoteComponentUI extends Group {

        public MusicalNoteComponentUI(ControlP5 theControlP5, String theName) {
            super(theControlP5, theName);
        }
    }
}

