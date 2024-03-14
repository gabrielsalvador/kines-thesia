package me.gabrielsalvador.pobject;

import me.gabrielsalvador.midi.MidiManager;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.pobject.components.body.shape.RectanglePShape;
import me.gabrielsalvador.pobject.components.controllers.CommandController;
import me.gabrielsalvador.utils.MusicalNote;
import processing.core.PGraphics;
import java.util.Arrays;


public class PMessage extends PObject {

    private String message = "empty";

    @PObject.InspectableProperty(displayName = "message", controllerClass = CommandController.class)
    public String getMessage() {
        return message;
    }

    @PObject.InspectableProperty.SetterFor("message")
    public void setMessage(String message) {
        this.message = message;
    }



    public PMessage() {
        super();
        initialize();

        RoutingComponent rc = new RoutingComponent(this);
        rc.setPulseCallback((message) -> {
            setMessage(Arrays.stream(message).reduce("", (a, b) -> a.toString() + b.toString() + " ").toString());
            MidiManager.getInstance().setChord(((MusicalNote )message[0]).getPitch());
        });
        addComponent(RoutingComponent.class, rc);

        PMessageBody body = new PMessageBody(this);
        body.setShape(new RectanglePShape(100, 50));
        addComponent(BodyComponent.class, body);
    }


    @Override
    public void initialize() {
        super.initialize();



    }




    class PMessageBody extends HologramBody {

        PMessage owner;
        public PMessageBody(PObject pObject) {
            super(pObject);
        }

        @Override
        public void display(PGraphics g) {
            g.pushMatrix();
            g.translate(getPixelPosition().x, getPixelPosition().y);
            RectanglePShape shape = (RectanglePShape) getShape();
            g.rect(0, 0, shape.getSize().x, shape.getSize().y);
            g.fill(255);
            g.textSize(20);
            g.text(message, 0, 0);
            g.popMatrix();
        }


    }

}
