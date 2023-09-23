package me.gabrielsalvador.sequencing;

public class Trigger implements MusicEvent{
    private final int _pitch;
    private final int _velocity;

    public Trigger(int pitch, int velocity) {
        _pitch = pitch;
        _velocity = velocity;
    }

    @Override
    public void play() {
    }
}
