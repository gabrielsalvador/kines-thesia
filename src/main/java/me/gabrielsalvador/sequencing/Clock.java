package me.gabrielsalvador.sequencing;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clock {

    private static Clock _instance;
    private ScheduledExecutorService executorService;
    private TransportState _transportState = TransportState.STOPPED;
    private int _tempo = 3;

    private Clock() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        startTickExecutor();
        AppController.getInstance().addPropertyChangeListener("tempo", evt -> {
            setTempo((int) evt.getNewValue());
        });
    }

    public synchronized static Clock getInstance() {
        if (_instance == null) {
            _instance = new Clock();
        }
        return _instance;
    }

    public void setTempo(int tempo) {
        this._tempo = tempo;
        restartTickExecutor();
    }

    private void startTickExecutor() {
        if (executorService.isShutdown() || executorService.isTerminated()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> {
                try {
                    for (Object d : AppState.getInstance().getPObjects()) {
                        if (!(d instanceof Device)) {
                            continue;
                        }
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        ((Device)d).clockTick();
                    }
                    SequencerController sequencerController = AppController.getInstance().getSequencerController();
                    //TODO:instead of sending clock tick to all devices, and then sending clock tick to sequencer, create a list of devices that need to receive clock tick
                    if (sequencerController != null) {
                        sequencerController.clockTick();
                    }


                } catch (Exception e) {
                    System.err.println("Exception caught inside the task.");
                    e.printStackTrace();
                }
            }, 0, getPeriod(), TimeUnit.MILLISECONDS);
            _transportState = TransportState.PLAYING;
        }
    }

    private int getPeriod() {
        return 60000 / (_tempo * 16);
    }



    private void restartTickExecutor() {
        pause();
        startTickExecutor();
    }

    public void togglePlay() {
        if (executorService.isShutdown() || executorService.isTerminated()) {
            play();
        } else {
            pause();
        }
    }

    public void play() {
        startTickExecutor();
    }

    public void pause() {
        if (!executorService.isShutdown()) {
            executorService.shutdownNow();
            _transportState = TransportState.PAUSED;
        }
    }

    public TransportState getTransportState() {
        return _transportState;
    }
}
