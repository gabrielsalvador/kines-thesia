package me.gabrielsalvador.pobject.components.musicalnote;


import controlP5.*;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.Component;
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

    @PObject.InspectableProperty.ControllerFor("Interval")
    private Keyboard intervalController;

    private int midiChannel = 1;

    @PObject.InspectableProperty(displayName = "Midi Channel", controllerClass = MidiChannelUI.class)
    public int getMidiChannel() {
        return midiChannel;
    }

    @PObject.InspectableProperty.SetterFor("Midi Channel")
    public void setMidiChannel(int midiChannel) {
        this.midiChannel = midiChannel;
    }


    public class MidiChannelUI extends DropdownList  {
        public MidiChannelUI(ControlP5 theControlP5, String theName) {
            super(theControlP5, theName);

            setBarHeight(20);
            setItemHeight(20);

            for (int i = 1; i <= 16; i++) {
                addItem("Channel " + i, i);
                close();
            }

            addCallback(new CallbackListener() {
                @Override
                public void controlEvent(CallbackEvent callbackEvent) {

                    Controller me = callbackEvent.getController();
                    me.bringToFront();

                    if (callbackEvent.getAction() == ControlP5.ACTION_RELEASE) {
                        setValue((int) callbackEvent.getController().getValue());
                        setMidiChannel((int) callbackEvent.getController().getValue());
                        System.out.println("Midi Channel: " + midiChannel);
                    }
                }
            });
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



