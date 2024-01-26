package me.gabrielsalvador.utils;

import java.util.Map;

import me.gabrielsalvador.utils.MusicalNote;

public class Scale {

    private final MusicalNote _root; // Now using MusicalNote
    private final int[] _intervals;
    private final Mode _mode;

    public Scale(String root, int accidental, Mode mode){
        int pitch = noteNameToPitch(root, accidental);
        _root = new MusicalNote(pitch);
        _mode = mode;
        _intervals = mode.getIntervals();
    }

    // Static method remains the same
    public static int noteNameToPitch(String noteName, int accidental) {
        int pitch = 0;
        switch(noteName){
            case "C":
                pitch = 0;
                break;
            case "C#":
                pitch = 1;
                break;
            case "D":
                pitch = 2;
                break;
            case "D#":
                pitch = 3;
                break;
            case "E":
                pitch = 4;
                break;
            case "F":
                pitch = 5;
                break;
            case "F#":
                pitch = 6;
                break;
            case "G":
                pitch = 7;
                break;
            case "G#":
                pitch = 8;
                break;
            case "A":
                pitch = 9;
                break;
            case "A#":
                pitch = 10;
                break;
            case "B":
                pitch = 11;
                break;
        }
        pitch += accidental;
        return pitch;

    }

    public MusicalNote getNoteFromInterval(int interval) {
        int pitch = _root.getPitch();
        for (int i = 0; i < interval; i++) {
            pitch += _intervals[i];
        }
        return new MusicalNote(pitch);
    }

    public MusicalNote getRoot() {
        return _root;
    }
    private int getRootPitch() {
        return _root.getPitch();
    }

    public int getPitchFromInterval(int interval) {
        int pitch = getRootPitch();
        for (int i = 0; i < interval; i++) {
            pitch += _intervals[i];
        }
        return pitch;
    }
}


