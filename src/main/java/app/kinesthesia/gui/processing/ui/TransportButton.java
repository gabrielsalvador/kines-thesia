package app.kinesthesia.gui.processing.ui;

import app.kinesthesia.core.Kinesthesia;
import app.kinesthesia.core.TransportState;
import controlP5.*;
import app.kinesthesia.core.Clock;
import app.kinesthesia.gui.processing.ResourceManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TransportButton extends Button implements PropertyChangeListener, CallbackListener {


    public TransportButton(ControlP5 theControlP5, String theName) {
            super(theControlP5, theName);

            Kinesthesia.getInstance().addPropertyChangeListener("transport", this);
            setIcon();
            addCallback(this);

        }

        public void setIcon(){
            TransportState state = Clock.getInstance().getTransportState();

            if(state == TransportState.PLAYING) {
                setImages(ResourceManager.getInstance().loadIconStates("play"));
            }
            else{
                setImages(ResourceManager.getInstance().loadIconStates("pause"));
            }

        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("transport")) {
            setIcon();
        }

    }

    @Override
    public void controlEvent(CallbackEvent theEvent) {
        if (theEvent.getAction() == ControlP5.ACTION_RELEASE) {
            Clock.getInstance().togglePlay();
        }
    }


    //listen for transport events

}
