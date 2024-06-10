package me.gabrielsalvador.utils;


import java.util.HashMap;

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

    public static HashMap<Integer,MusicalNote> intervalsMemo = new HashMap<Integer,MusicalNote>();

    public Scale(int[] intervals) {
        this(intervals, new MusicalNote("C1"));
    }

    public Scale(int[] intervals, MusicalNote root) {
        _intervals = intervals;
        _root = root;
    }

    public MusicalNote doInterval(int interval) {

        if (intervalsMemo.containsKey(interval)) {
            return intervalsMemo.get(interval);
        }

        if (_root == null) {
            throw new IllegalStateException("Root note not set");
        }

        int pitch = _root.getPitch();

        if (interval == 0) {
            return new MusicalNote(pitch);
        }

        int octaveMultiplier = 0;
        while (interval < 0 || interval > _intervals.length) {
            if (interval < 0) {
                octaveMultiplier--;
                interval += _intervals.length;
            } else {
                octaveMultiplier++;
                interval -= _intervals.length;
            }
        }

        for (int i = 0; i < interval; i++) {
            //this can be memoized
            pitch += _intervals[i];
        }


        MusicalNote note =  new MusicalNote(pitch + (octaveMultiplier * 12));
        intervalsMemo.put(interval, note);
        return note;
    }

    public Scale setRoot(MusicalNote note) {
        intervalsMemo.clear();
        _root = note;
        return this;
    }
}
