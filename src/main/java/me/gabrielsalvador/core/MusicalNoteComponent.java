package me.gabrielsalvador.core;



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

    @PObject.InspectableProperty(displayName = "Midi Channel")
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



