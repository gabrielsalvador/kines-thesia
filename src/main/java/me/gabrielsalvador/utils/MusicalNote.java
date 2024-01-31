package me.gabrielsalvador.utils;

import java.io.Serializable;

public class MusicalNote implements Serializable {

    private final int _root; //in midi pitch

    public MusicalNote (int root){
        _root = root;
    }

    public String getFullName(){
        return getName()+ getOctave();
    }

    public String getName(){
        int note = _root % 12;
        switch(note){
            case 0:
                return "C";
            case 1:
                return "C#";
            case 2:
                return "D";
            case 3:
                return "D#";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "F#";
            case 7:
                return "G";
            case 8:
                return "G#";
            case 9:
                return "A";
            case 10:
                return "A#";
            case 11:
                return "B";
        }
        return null;
    }

    public int getOctave(){
        return _root / 12 - 1;
    }

    public int getPitch(){
        return _root;
    }

}
