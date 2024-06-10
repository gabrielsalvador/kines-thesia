package me.gabrielsalvador.utils;


public class MusicalNote {

    private  int _pitch;

    public MusicalNote(String note) {
        int octave = 0;
        if (note.contains("-")) {
            String[] parts = note.split("-");
            note = parts[0];
            octave = -Integer.parseInt(parts[1]);
        } else if (Character.isDigit(note.charAt(note.length() - 1))) {
            octave = Integer.parseInt(note.substring(note.length() - 1));
            note = note.substring(0, note.length() - 1);
        }
        int pitch = MathUtils.noteLetterToPitch(note);
        _pitch = pitch + (octave * 12);
    }


    public MusicalNote(int pitch) {
        _pitch = pitch;
    }



    public int getPitch() {
        return _pitch;
    }


    public String toString() {
        if (_pitch < 0) {
            _pitch = 0; //brickwall
        }
        return MathUtils.pitchToNoteLetter(_pitch) + (int) (double) ((_pitch / 12)-1);
    }


}

