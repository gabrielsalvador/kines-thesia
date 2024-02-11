package me.gabrielsalvador.utils;

import com.lowagie.text.factories.RomanNumberFactory;

public enum Interval {
    UNISON, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH, OCTAVE;

    int octave = 0;

    public static Interval fromScaleDegree(int i) {
        return values()[i];
    }

    public Interval octave(int octave) {
        this.octave = octave;
        return this;
    }


    public int toSemitones(Scale scale) {
        int semitones = 0;
        switch (this) {
            case UNISON:
                    break;
            case SECOND:
                if (scale == Scale.MAJOR) {
                    semitones = 2;
                    break;
                }
                if (scale == Scale.MINOR) {
                    semitones = 1;
                    break;
                }
            case THIRD:
                if (scale == Scale.MAJOR) {
                    semitones = 4;
                    break;
                }
                if (scale == Scale.MINOR) {
                    semitones = 3;
                    break;
                }
            case FOURTH:
                if (scale == Scale.MAJOR) {
                    semitones = 5;
                    break;
                }
                if (scale == Scale.MINOR) {
                    semitones = 6;
                    break;
                }
            case FIFTH:
                semitones = 7;
                break;
            case SIXTH:
                if (scale == Scale.MAJOR) {
                    semitones = 9;
                    break;
                }
                if (scale == Scale.MINOR) {
                    semitones = 8;
                    break;
                }
            case SEVENTH:
                if (scale == Scale.MAJOR) {
                    semitones = 11;
                    break;
                }
                if (scale == Scale.MINOR) {
                    semitones = 10;
                    break;
                }
            case OCTAVE:
                semitones = 12;
                break;
        }

        semitones += this.octave * 12;
        return semitones;
    }

    public String getName() {
        //to roman numeral
        return RomanNumberFactory.getUpperCaseString(this.ordinal()+1);
    }

    public Interval plus(Interval interval) {
        return values()[(this.ordinal() + interval.ordinal()) % values().length];
    }

    public Interval minus(Interval interval) {
        return values()[(this.ordinal() - interval.ordinal() + values().length) % values().length];
    }
}

