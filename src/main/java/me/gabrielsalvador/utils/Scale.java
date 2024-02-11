package me.gabrielsalvador.utils;


public class Scale {
    public static final Scale MAJOR = new Scale(new int[]{2, 2, 1, 2, 2, 2, 1});
    public static final Scale MINOR = new Scale(new int[]{2, 1, 2, 2, 1, 2, 2});
    public static final Scale HARMONIC_MINOR = new Scale(new int[]{2, 1, 2, 2, 1, 3, 1});
    public static final Scale MELODIC_MINOR = new Scale(new int[]{2, 1, 2, 2, 2, 2, 1});
    public static final Scale PENTATONIC = new Scale(new int[]{3, 2, 2, 3, 2});
    public static final Scale BLUES = new Scale(new int[]{3, 2, 1, 1, 3, 2});
    public static final Scale WHOLE_TONE = new Scale(new int[]{2, 2, 2, 2, 2, 2});
    public static final Scale CHROMATIC = new Scale(new int[]{});
    public static final Scale HIRAJOSHI = new Scale(new int[]{2, 1, 4, 1, 4});



    private final int[] _intervals;
    private MusicalNote _root;

    public Scale(int[] intervals) {
        this(intervals, new MusicalNote("C1"));
    }

    public Scale(int[] intervals, MusicalNote root) {
        _intervals = intervals;
        _root = root;
    }

    public MusicalNote doInterval( int interval) {

        int pitch = _root.getPitch();

        if (interval == 0) {
            return new MusicalNote(pitch);
        }

        if (interval < 0) {
            interval = _intervals.length + interval;
        }

        for (int i = 0; i < interval; i++) {
            pitch += _intervals[i % _intervals.length];
        }
        return new MusicalNote(pitch);


    }

    public Scale setRoot(MusicalNote note) {
        _root = note;
        return this;
    }
}
