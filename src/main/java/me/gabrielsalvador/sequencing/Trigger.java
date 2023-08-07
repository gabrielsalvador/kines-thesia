package me.gabrielsalvador.sequencing;

public class Trigger implements MusicEvent{
    private int _pitch;
    private int _velocity;

    public Trigger(int pitch, int velocity) {
        _pitch = pitch;
        _velocity = velocity;
    }

    @Override
    public void play() {
    }
}
