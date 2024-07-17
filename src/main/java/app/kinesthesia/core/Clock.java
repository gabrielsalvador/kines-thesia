package app.kinesthesia.core;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clock {

    private static Clock _instance;
    private ScheduledExecutorService executorService;
    private TransportState _transportState = TransportState.STOPPED;
    private int _tempo = 120;
    //the time when the last tick started
    public long _lastTickTime ; //in nanoseconds
    private final int _periodIn16thNotes = 16; //how many 16th notes are sent per quarter note

    private Clock() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
//        startTickExecutor();
        Kinesthesia.getInstance().addPropertyChangeListener("tempo", evt -> {
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
        //only restart the clock if it is already playing
        if (_transportState == TransportState.PLAYING)
            restartTickExecutor();
    }

    private void startTickExecutor() {
        if (executorService.isShutdown() || executorService.isTerminated()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        executorService.scheduleAtFixedRate(() -> {
            _lastTickTime = System.nanoTime();
            try {
                List<Object> pObjectsSnapshot = new ArrayList<>(AppState.getInstance().getPObjects());


                for (Object d : pObjectsSnapshot) {
                    if (!(d instanceof Device)) {
                        continue;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    ((Device)d).clockTick();
                }
//                SequencerController sequencerController = AppController.getInstance().getSequencerController();
//                if (sequencerController != null) {
//                    sequencerController.clockTick();
//                }
            } catch (Exception e) {
                Kinesthesia.defaultExceptionHandler.uncaughtException(Thread.currentThread(), e);
            }
        }, 0, getPeriodOfNthNotes(_periodIn16thNotes), TimeUnit.NANOSECONDS);
        _transportState = TransportState.PLAYING;
    }


    private void restartTickExecutor() {
        pause();
        startTickExecutor();
        Kinesthesia.getInstance().firePropertyChange("transport", null, getTransportState());
    }

    public void togglePlay() {
        TransportState oldState = getTransportState();
        if (oldState != TransportState.PLAYING) {
            play();
        } else {
            pause();
        }
        Kinesthesia.getInstance().firePropertyChange("transport", oldState, getTransportState());
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

    public void scheduleTask(Runnable task, long delay) {
        if (executorService.isShutdown() || executorService.isTerminated()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        executorService.schedule(task, delay, TimeUnit.NANOSECONDS);
    }


    public long getPeriodOfNthNotes(int division) {
        return (long) (1_000_000_000.0 / (_tempo / 60.0) / (division/4.f));
    }


    //how much time in nanoseconds should pass before the next tick

    public long getTimeUntilNextTick() {
        long period = getPeriodOfNthNotes(_periodIn16thNotes);
        long timeSinceLastTick = System.nanoTime() - _lastTickTime;
        return period - timeSinceLastTick;

    }


    public void shutdown() {
        executorService.shutdown();
        _transportState = TransportState.STOPPED;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executorService.awaitTermination(timeout, unit);
    }

    public void forceShutdown() {
        executorService.shutdownNow();
    }


}
