package me.gabrielsalvador.utils;



import java.io.Serializable;

public class ScaleNote implements Serializable {

    private int _degree; //1 to 7
    private int _octave;
    private Scale _scale;

    public ScaleNote(int degree){
        this(degree, 4, Scale.MAJOR);

    }

    public ScaleNote(int degree, Scale scale){
        this(degree, 4, scale);

    }

    public ScaleNote(int degree, int octave, Scale scale){
        _degree = degree;
        _octave = octave;
        _scale = scale;
    }


    public int getPitch(){
        return _scale.degreeToPitch(_octave, _degree);
    }

    public String getFullName() {
        // show _degree in roman numeral
        String roman = "";
        switch(_degree){
            case 1:
                roman = "I";
                break;
            case 2:
                roman = "II";
                break;
            case 3:
                roman = "III";
                break;
            case 4:
                roman = "IV";
                break;
            case 5:
                roman = "V";
                break;
            case 6:
                roman = "VI";
                break;
            case 7:
                roman = "VII";
                break;
        }
        return roman;

    }
}
