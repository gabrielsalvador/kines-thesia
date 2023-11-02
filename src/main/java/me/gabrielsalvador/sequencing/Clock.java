package me.gabrielsalvador.sequencing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clock {

    private static Clock _instance;
    private final List<Device> _devices = new ArrayList<>();
    private ScheduledExecutorService executorService;
    private TransportState _transportState = TransportState.STOPPED;
    private int _tempo = 10;

    private Clock() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        startTickExecutor();
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
                    for (Device d : _devices) {
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        d.clockTick();
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

    public void addDevice(Device device) {
        _devices.add(device);
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
