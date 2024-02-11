package me.gabrielsalvador.utils;

public enum Scale {

    MAJOR, MINOR, PENTATONIC;


    Scale() {
    }



    public int getIntervalSemitones(Interval interval) {
        if (this == MAJOR) {
            switch (interval) {
                case UNISON:
                    return 0;
                case SECOND:
                    return 2;
                case THIRD:
                    return 4;
                case FOURTH:
                    return 5;
                case FIFTH:
                    return 7;
                case SIXTH:
                    return 9;
                case SEVENTH:
                    return 11;
                case OCTAVE:
                    return 12;
            }
        }
        return 0;
    }

    public MusicalNote getChordNote(MusicalNote root, Interval degree) {
        int semitones = getIntervalSemitones(degree);
        return root.applyInterval(degree, this);

    }

}
