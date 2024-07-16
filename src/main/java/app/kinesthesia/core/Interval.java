package app.kinesthesia.core;


import java.io.Serializable;

//wrapper for a int to be used as a setter in the editor
public class Interval implements Serializable {
    public int interval;

    public Interval(int interval) {
        this.interval = interval;
    }

//    public String getName() {
////        int i = interval % 7;
////        if (i == 0) return "I";
////        if (i == 1) return "II";
////        if (i == 2) return "III";
////        if (i == 3) return "IV";
////        if (i == 4) return "V";
////        if (i == 5) return "VI";
////        else return "VII";
//    }
}