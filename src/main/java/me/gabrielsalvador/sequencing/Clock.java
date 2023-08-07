package me.gabrielsalvador.sequencing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clock {

    private static Clock _instance;
    private final List<Device> _devices = new ArrayList<Device>();
    private final ScheduledExecutorService executorService;
    private int _tempo = 120;

    private Clock() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        startTickExecutor();
    }

    public synchronized static Clock getInstance() {
        if(_instance == null) {
            _instance = new Clock();
        }
        return _instance;
    }

    public void setTempo(int tempo) {
        this._tempo = tempo;
        restartTickExecutor();
    }

    private void tick() {
        for (Device d : _devices) {
            d.clockTick();
        }
    }

    private int getPeriod() {
        return 60000 / (_tempo * 16);
    }

    public void addDevice(Device device) {
        _devices.add(device);
    }

    private void startTickExecutor() {
        executorService.scheduleAtFixedRate(this::tick, 0, getPeriod(), TimeUnit.MILLISECONDS);
    }

    private void restartTickExecutor() {
        executorService.shutdownNow();
        startTickExecutor();
    }
}