package me.gabrielsalvador.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import processing.core.PVector;

public class PObject {
    PVector position;
    PVector size;
    public final Set<PObject> _children = new HashSet<PObject>();
    HashMap<String, PObjectProperty<?>> properties = new HashMap<String, PObjectProperty<?>>();


}