package me.gabrielsalvador.pobject.components.musicalnote;


import controlP5.ControlP5;
import controlP5.DropdownList;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.views.PropertyInspectorController;
import me.gabrielsalvador.utils.Interval;
import org.jbox2d.common.Vec2;
import processing.core.PConstants;
import processing.core.PGraphics;

public class MusicalNoteComponent extends Component {

    private Interval interval;

    @PObject.InspectableProperty(displayName = "Interval")
    public Interval getInterval() {
        return interval;
    }

    @PObject.InspectableProperty.SetterFor("Interval")
    public void setInterval(Interval interval) {
        this.interval = interval;
    }


    private int midiChannel = 1;
    @PObject.InspectableProperty(displayName = "Midi Channel")
    public int getMidiChannel() {return midiChannel;}
    @PObject.InspectableProperty.SetterFor("Midi Channel")
    public void setMidiChannel(int midiChannel) {this.midiChannel = midiChannel;}

    @SuppressWarnings("unused")
    @PObject.InspectableProperty.ControllerFor("Midi Channel")
    class MidiChannelUI extends DropdownList implements PropertyInspectorController {
        public MidiChannelUI(ControlP5 theControlP5, String theName) {
            super(theControlP5, theName);
        }

        @Override
        public void setProperty(PObjectProperty property) {
            for (int i = 1; i <= 16; i++) {
                addItem("Channel " + i, i);
            }
            setValue(midiChannel);

        }
    }



    private final PObject owner;

    public MusicalNoteComponent(PObject owner, int note) {
        super(owner);
        this.owner = owner;
        interval = new Interval(note);

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





}



