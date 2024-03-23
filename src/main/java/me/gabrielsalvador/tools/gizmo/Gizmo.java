package me.gabrielsalvador.tools.gizmo;

import me.gabrielsalvador.PGroup;
import me.gabrielsalvador.pobject.PObject;
import org.jbox2d.common.Vec2;
import processing.core.PGraphics;

public abstract class Gizmo {

    protected PGroup selectedObjects;
    public boolean _isDragging = false;
    protected boolean _mouseIsDown = false;
    protected Vec2 _initialDragPosition;

    public Gizmo(PGroup _group) {
        if(_group.isEmpty()){
            throw new RuntimeException("Cannot create a gizmo with an empty group");
        }
        selectedObjects = _group;
    }
    public void draw(PGraphics graphics) {}

    public abstract void onPressed() ;

    public boolean isInside(int[] mousePosition) {
        //hitcircle is 10x10
        Vec2[] positions = getPositions();
        for (Vec2 position : positions) {
            //if is inside the circle
            float distance = position.sub(new Vec2(mousePosition[0], mousePosition[1])).length();
            if (distance < 10) {
                return true;
            }

        }
        return false;
    }

    public abstract void onDrag(PObject pObject, int[] mousePosition);

    public abstract Vec2[] getPositions();


    public void onRelease(PObject pObject) {
        _mouseIsDown = false;
        _isDragging = false;
        System.out.println("gizmo released");
    }

    public abstract void onDragStart(PObject pObject, int[] mousePosition);

    public boolean getMouseIsDown() {
        return _mouseIsDown;
    }

    public void setMouseDown(boolean b) {
        _mouseIsDown = b;
    }

    public void setInitialDragPosition(Object o) {
        _initialDragPosition = (Vec2) o;
    }
}

