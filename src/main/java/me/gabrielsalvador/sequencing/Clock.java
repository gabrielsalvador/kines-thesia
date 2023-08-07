package me.gabrielsalvador.sequencing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Clock {

    private final List<Device> _devices = new ArrayList<Device>();
    private final ScheduledExecutorService executorService;
    private int _tempo = 120;


    public Clock() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }



    public void setTempo(int tempo) {

    }

    private void tick() {
        for (Device d  :_devices) {
            d.clock();
        }

    }

    private int getPeriod() {

        return (60000 / (_tempo * 32));
    }

    public void addDevice(Device device) {


    }

}
