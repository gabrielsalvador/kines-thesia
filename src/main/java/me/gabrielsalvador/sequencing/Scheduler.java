package me.gabrielsalvador.sequencing;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.*;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final Map<String, List<MusicEvent>> events;
    private final ScheduledExecutorService executorService;
    private int currentTick;
    private int _tempo = 120;
    private ScheduledFuture<?> currentTask;

    public Scheduler() {
        this.events = new HashMap<>();
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.currentTick = 0;
    }

    public void addEvent(String device, MusicEvent event, int tick) {
        if (tick < 0 || tick > 31) {
            throw new IllegalArgumentException("Tick must be between 0 and 31");
        }

        events.computeIfAbsent(device, k -> new ArrayList<>());

        List<MusicEvent> deviceEvents = events.get(device);
        if (deviceEvents.size() <= tick) {
            while(deviceEvents.size() <= tick) {
                deviceEvents.add(null);  // fill with nulls till the required tick
            }
        }
        deviceEvents.set(tick, event);
    }

    public void start() {
        if (currentTask != null) {
            currentTask.cancel(false);
        }
        currentTask = executorService.scheduleAtFixedRate(this::play, 0, getPeriod(), TimeUnit.MILLISECONDS);
    }

    public void stop() {
        if (currentTask != null) {
            currentTask.cancel(false);
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public void setTempo(int tempo) {
        if (tempo <= 0) {
            throw new IllegalArgumentException("Tempo must be positive");
        }
        this._tempo = tempo;
        start();  // restart the executor with the new period
    }

    private void play() {
        for (List<MusicEvent> deviceEvents : events.values()) {
            if (currentTick >= deviceEvents.size() || deviceEvents.get(currentTick) == null) {
                continue;  // skip if beyond size or if event at tick is null
            }

            MusicEvent event = deviceEvents.get(currentTick);
            event.play();
        }

        currentTick++;
        if (currentTick > 31) {
            currentTick = 0;
        }
    }

    private int getPeriod() {
        // This formula assumes 32 ticks per beat, adjust if different
        return (60000 / (_tempo * 32));
    }

    public void addTrack(String device) {
        if (events.containsKey(device)) {
            throw new IllegalArgumentException("Track/device " + device + " already exists");
        }
        // Initialize with 32 slots, as there are 32 ticks
        List<MusicEvent> deviceEvents = new ArrayList<>(Collections.nCopies(32, null));
        events.put(device, deviceEvents);
    }

}
