package me.gabrielsalvador.utils;

import java.util.Map;

public class Scale {

    private final int _root; //in midi pitch
    private final int[] _intervals;
    private final Mode _mode;



    /* root = "C3", "C" ,
    accidental = -1 for flat, 0 for natural, 1 for sharp */
    public Scale(String root,int accidental, Mode mode){
        _root = noteNameToPitch(root,accidental);
        _mode = mode;
        _intervals = mode.getIntervals();
    }



    //return midi pitch for a note expressed in Letter Notation
    public static int noteNameToPitch(String noteName, int accidental) {
        // Mapping from note symbols to their pitch offsets from C
        Map<Character, Integer> noteOffsets = Map.of(
                'C', 0,
                'D', 2,
                'E', 4,
                'F', 5,
                'G', 7,
                'A', 9,
                'B', 11
        );

        char noteSymbol = noteName.charAt(0);
        int octave = Integer.parseInt(noteName.substring(1));

        int basePitch = 12 * (octave + 1) + noteOffsets.get(noteSymbol);

        return basePitch + accidental;
    }

    public int getPitchFromInterval(int interval){
        int pitch = _root;
        for(int i = 0; i < interval; i++){
            pitch += _intervals[i];
        }
        return pitch;
    }

}



