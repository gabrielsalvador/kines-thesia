package me.gabrielsalvador.gui.processing.tools.gizmo;

import me.gabrielsalvador.core.BodyComponent;
import me.gabrielsalvador.core.MathUtils;
import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.PGroup;
import org.jbox2d.common.Vec2;
import processing.core.PApplet;
import processing.core.PGraphics;

public class FreetransformGizmo extends Gizmo {




    private float rotatingAngle = 0;
    private float _initialAngle = 0; //in degrees

    public FreetransformGizmo(PGroup selectedObjects) {
        super(selectedObjects);
    }

    @Override
    public void draw(PGraphics graphics) {


        graphics.pushStyle();
        graphics.fill(255, 0, 0);
        Vec2[] _position = getPositions();
        graphics.ellipseMode(PApplet.CENTER);
        graphics.fill(255, 255, 255);
        graphics.stroke(0);
        for (Vec2 position : _position) {
            graphics.ellipse(position.x, position.y, 10, 10);
        }
        graphics.popStyle();


        //draw visual aid for rotation
//        if( bufferedMousePosition != null && _initialDragPosition != null){
//            graphics.pushStyle();
//            graphics.stroke(255, 0, 0);
//            Vec2 center = _selectedObjects.getPixelCenter();
//            graphics.line(center.x, center.y, bufferedMousePosition.x, bufferedMousePosition.y);
//
//            //draw line from center to _initialDragPosition
//            graphics.stroke(0, 255, 0);
//            graphics.line(center.x, center.y, _initialDragPosition.x, _initialDragPosition.y);
//
//            graphics.noFill();
//            //distance between center and _initialDragPosition
//            graphics.stroke(0, 0, 255);
//            float distance = bufferedMousePosition.sub(center).length();
//            graphics.ellipseMode(PApplet.CENTER);
//            graphics.circle(center.x, center.y, distance * 2);
//
//           graphics.popStyle();
//        }


    }

    @Override
    public void onPressed() {
        float initialAngle = selectedObjects.get(0).getBodyComponent().getAngle();
        System.out.println("FreetransformGizmo pressed" + initialAngle);
    }



    public void onDragStart(PObject pObject, int[] mousePosition) {
        System.out.println("dragging started");
        _initialAngle = selectedObjects.get(0).getBodyComponent().getAngle() * 180 / (float) Math.PI;
        //convert to degrees
        _initialAngle = (_initialAngle) % 360;
    }


    public void onDrag(PObject pObject, int[] mousePosition) {

//        bufferedMousePosition = new Vec2(mousePosition[0], mousePosition[1]);

        //rotation angle between _initialDragPosition center and mousePosition
        Vec2 center = selectedObjects.getPixelCenter();
        Vec2 mousePositionVec = new Vec2(mousePosition[0], mousePosition[1]);
        Vec2 initialDragPositionVec = new Vec2(_initialDragPosition.x, _initialDragPosition.y);
        Vec2 centerToMouse = mousePositionVec.sub(center);
        Vec2 centerToInitialDragPosition = initialDragPositionVec.sub(center);
        rotatingAngle = MathUtils.calculateAngle(centerToMouse, centerToInitialDragPosition); //this is in degrees

        //rotate selected objects
        for (PObject selectedObject : selectedObjects.getItems()) {
            if (!Float.isNaN(rotatingAngle)) {

                selectedObject.getBodyComponent().rotateBodyAroundPivot(center, _initialAngle + rotatingAngle);
                System.out.println(_initialAngle + " : " + rotatingAngle);
            }

        }
    }


    @Override
    public Vec2[] getPositions() {
        if (selectedObjects.isEmpty()) {
            return new Vec2[]{new Vec2(0, 0)};
        }

        BodyComponent body = selectedObjects.get(0).getBodyComponent();
        Vec2 position = body.getPosition();
        float[] dimensions = body.getShape().getBoundaries();
        float rotation = body.getAngle();

        Vec2[] vertices = new Vec2[4];


        for (int i = 0; i < 4; i++) {
            double offsetX = (i % 2 == 0 ? dimensions[2] : -dimensions[2]) * 10;
            double offsetY = (i < 2 ? dimensions[3] : -dimensions[3]) * 10;

            double rotatedX = position.x + offsetX * Math.cos(rotation) - offsetY * Math.sin(rotation);
            double rotatedY = position.y + offsetX * Math.sin(rotation) + offsetY * Math.cos(rotation);

            vertices[i] = new Vec2((float) rotatedX, (float) rotatedY);
        }

        return vertices;
    }

}
