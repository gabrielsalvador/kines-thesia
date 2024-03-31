package me.gabrielsalvador.ui;

import controlP5.*;
import me.gabrielsalvador.ResourceManager;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.timing.Clock;
import me.gabrielsalvador.timing.TransportState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TransportButton extends Button implements PropertyChangeListener, CallbackListener {


    public TransportButton(ControlP5 theControlP5, String theName) {
            super(theControlP5, theName);

            AppController.getInstance().addPropertyChangeListener("transport", this);
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
