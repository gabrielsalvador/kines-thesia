package me.gabrielsalvador.pobject.components.musicalnote;



import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.ui.MidiChannelEditor;
import me.gabrielsalvador.utils.Interval;
import me.gabrielsalvador.utils.MusicalNote;
import org.jbox2d.common.Vec2;
import processing.core.PConstants;
import processing.core.PGraphics;

public class MusicalNoteComponent extends Component {

    Interval intervalToPlay; //relative to the root note + the current chord
    @PObject.InspectableProperty(displayName = "Play Note")
    public Interval getInterval() {
        return intervalToPlay;
    }

    @PObject.InspectableProperty.SetterFor("Play Note")
    public void setInterval(int intervalsPitch) {
        intervalToPlay = new Interval(intervalsPitch);
    }


    private int midiChannel = 1;

    @PObject.InspectableProperty(displayName = "Midi Channel", controllerClass = MidiChannelEditor.class)
    public int getMidiChannel() {
        return midiChannel;
    }

    @PObject.InspectableProperty.SetterFor("Midi Channel")
    public void setMidiChannel(int midiChannel) {
        this.midiChannel = midiChannel;
    }


    private final PObject owner;

    public MusicalNoteComponent(PObject owner, int note) {
        super(owner);
        this.owner = owner;
        intervalToPlay = new Interval(note);
    }

    public MusicalNote getNote() {
        int currentChord = MidiManager.getInstance().getChord();
        return MidiManager.getInstance().getScale().doInterval(intervalToPlay.interval + currentChord);
    }

    @Override
    public void display(PGraphics graphics) {
        MusicalNote note = getNote();
        graphics.textSize(10);
        graphics.textAlign(PConstants.CENTER, PConstants.CENTER);
        Vec2 center = owner.getBodyComponent().getPixelCenter();
        graphics.fill(255);
        graphics.text(note.toString(), center.x, center.y);
    }

    @Override
    public String getName() {
        return "Musical Note";
    }

    @Override
    public void dispose() {

    }

    @Override
    public void remove() {

    }


}



