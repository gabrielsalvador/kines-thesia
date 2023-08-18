package me.gabrielsalvador.pobject.components;

import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.BodyType;
import java.io.Serializable;

public class BodyData implements Serializable {
    public float x, y;  // position
    public float angle;  // rotation
    public float linearVelocityX, linearVelocityY;
    public float angularVelocity;
    public BodyType bodyType;
    // ... Add more properties as needed, e.g., fixtures, mass properties, etc.
}