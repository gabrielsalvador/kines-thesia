package me.gabrielsalvador.utils;


import java.util.HashMap;

public class Scale {
    public static final Scale MAJOR = new Scale(new int[]{2, 2, 1, 2, 2, 2, 1}).setName("Major");
    public static final Scale MINOR = new Scale(new int[]{2, 1, 2, 2, 1, 2, 2}).setName("Minor");
    public static final Scale HARMONIC_MINOR = new Scale(new int[]{2, 1, 2, 2, 1, 3, 1}).setName("Harmonic Minor");
    public static final Scale MELODIC_MINOR = new Scale(new int[]{2, 1, 2, 2, 2, 2, 1}).setName("Melodic Minor");
    public static final Scale PENTATONIC = new Scale(new int[]{3, 2, 2, 3, 2}).setName("Pentatonic");
    public static final Scale BLUES = new Scale(new int[]{3, 2, 1, 1, 3, 2}).setName("Blues");
    public static final Scale WHOLE_TONE = new Scale(new int[]{2, 2, 2, 2, 2, 2}).setName("Whole Tone");
    public static final Scale CHROMATIC = new Scale(new int[]{}).setName("Chromatic");
    public static final Scale HIRAJOSHI = new Scale(new int[]{2, 1, 4, 1, 4}).setName("Hirajoshi");



    private final int[] _intervals;
    private MusicalNote _root;
    private String name;

    //where we memoize the notes we have already calculated
    public static HashMap<Integer,MusicalNote> notesMemo = new HashMap<Integer,MusicalNote>();
    //where we memoize the intervals we have already calculated
    public static HashMap<NotePair,Integer> intervalMemo = new HashMap<NotePair,Integer>();

    public Scale(int[] intervals) {
        this(intervals, new MusicalNote("C1"));
    }

    public Scale(int[] intervals, MusicalNote root) {
        _intervals = intervals;
        _root = root;
    }

    public MusicalNote doInterval(int interval) {

        if (notesMemo.containsKey(interval)) {
            return notesMemo.get(interval);
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
        notesMemo.put(interval, note);
        return note;
    }


    //returns the number of scale steps between two notes
    public int intervalBetween(MusicalNote note1, MusicalNote note2) throws Exception {

        if (intervalMemo.containsKey(new NotePair(note1, note2))) {
            return intervalMemo.get(new NotePair(note1, note2));
        }

        int numIntervals = 0;

        int pitch1 = note1.getPitch();
        int pitch2 = note2.getPitch();

        if (pitch1 == pitch2) {
            return 0;
        } else if (pitch1 < pitch2) {
            while (pitch1 < pitch2) {
                pitch1 += _intervals[numIntervals % _intervals.length];
                numIntervals++;
            }
        }else{ //if (pitch1 > pitch2)
            while (pitch1 > pitch2) {
                numIntervals--;
                pitch1 -= _intervals[(numIntervals % _intervals.length + _intervals.length) % _intervals.length];
            }
        }

        if( pitch1 != pitch2) {
            throw new Exception("Notes are not in the same scale");
        }

        intervalMemo.put(new NotePair(note1, note2), numIntervals);

        return numIntervals;

    }

    public Scale setRoot(MusicalNote note) {
        notesMemo.clear();
        _root = note;
        return this;
    }

    public MusicalNote getRoot() {
        return _root;
    }

    public Scale setName(String name) {
        this.name = name;
        return this;
    }

    public String toString() {
        return name;
    }


    public static class NotePair {
        public MusicalNote note1;
        public MusicalNote note2;

        public NotePair(MusicalNote note1, MusicalNote note2) {
            this.note1 = note1;
            this.note2 = note2;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NotePair) {
                NotePair other = (NotePair) obj;
                return other.note1.equals(note1) && other.note2.equals(note2);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return note1.hashCode() + note2.hashCode();
        }
    }
}
