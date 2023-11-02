package me.gabrielsalvador.pobject.views;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.sequencing.Clock;

public class PMetronome extends PObject {
    public PMetronome() {
        super();
        Clock.getInstance();
    }


}
