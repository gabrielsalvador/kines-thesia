package me.gabrielsalvador.utils;

import java.io.Serializable;

public class Mode implements Serializable {

    public static final Mode MAJOR = new Mode("Major");
    int[] intervals;

    /* index = 0 for major scale, 1 for minor scale 2 for dorian, 3 for phrygian, 4 for lydian, 5 for mixolydian, 6 for locrian */
    public Mode(int index){
        switch(index){
            case 0:
                intervals = new int[]{2,2,1,2,2,2,1};
                break;
            case 1:
                intervals = new int[]{2,1,2,2,1,2,2};
                break;
            case 2:
                intervals = new int[]{2,1,2,2,2,1,2};
                break;
            case 3:
                intervals = new int[]{1,2,2,2,1,2,2};
                break;
            case 4:
                intervals = new int[]{2,2,2,1,2,2,1};
                break;
            case 5:
                intervals = new int[]{2,2,1,2,2,1,2};
                break;
            case 6:
                intervals = new int[]{2,1,2,2,1,2,2};
                break;
        }

    }

    public Mode(String string) {
        switch(string){
            case "Major":
                intervals = new int[]{2,2,1,2,2,2,1};
                break;
            case "Minor":
                intervals = new int[]{2,1,2,2,1,2,2};
                break;
            case "Dorian":
                intervals = new int[]{2,1,2,2,2,1,2};
                break;
            case "Phrygian":
                intervals = new int[]{1,2,2,2,1,2,2};
                break;
            case "Lydian":
                intervals = new int[]{2,2,2,1,2,2,1};
                break;
            case "Mixolydian":
                intervals = new int[]{2,2,1,2,2,1,2};
                break;
            case "Locrian":
                intervals = new int[]{2,1,2,2,1,2,2};
                break;
        }

    }

    public int[] getIntervals() {
        return intervals;
    }
}
