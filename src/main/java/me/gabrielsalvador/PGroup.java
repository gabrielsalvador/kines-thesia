package me.gabrielsalvador;


import me.gabrielsalvador.pobject.PObject;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

//A group of PObjects
public class PGroup {



    private ArrayList<PObject> _objects = new ArrayList<>();

    public PGroup(ArrayList<PObject> objects){

        _objects = objects;

    }

    public int size() {
        return _objects.size();
    }

    public PObject get(int index) {
        return _objects.get(index);
    }

    public PObject[] getItems() {
        return _objects.toArray(new PObject[0]);
    }



    public Vec2 getPixelCenter() {

        Vec2 pixelCenter = new Vec2(0, 0);
        for (PObject object : _objects) {
            pixelCenter = pixelCenter.add(object.getBodyComponent().getPixelCenter());
        }
        pixelCenter.mul(1f / _objects.size());

        return pixelCenter;
    }
}
