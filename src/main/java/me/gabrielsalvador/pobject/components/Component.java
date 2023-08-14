package me.gabrielsalvador.pobject.components;


import me.gabrielsalvador.pobject.PObjectProperty;

import java.util.ArrayList;

public interface Component {


    public void update();

    public String getName();
    public ArrayList<PObjectProperty> getProperties();
}
