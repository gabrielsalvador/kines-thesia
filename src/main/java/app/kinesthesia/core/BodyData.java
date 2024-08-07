package app.kinesthesia.core;

import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.io.Serializable;

public class BodyData implements Serializable {
    public float x, y;  // position
    public float angle;  // rotation
    public float linearVelocityX, linearVelocityY;
    public float angularVelocity;
    public BodyType bodyType;
    public ShapeType shapeType;

    public Vec2[] vertices;
    public float circleRadius;
    public boolean isSensor;
    public boolean isBullet;

    public static BodyData getDefaultBodyData(){
        BodyData bodyData = new BodyData();
        bodyData.x = 0;
        bodyData.y = 0;
        bodyData.angle = 0;
        bodyData.linearVelocityX = 0;
        bodyData.linearVelocityY = 0;
        bodyData.angularVelocity = 0;
        bodyData.bodyType = BodyType.STATIC;
        bodyData.shapeType = ShapeType.CIRCLE;
        bodyData.circleRadius = 10;
        bodyData.isSensor = false;
        bodyData.isBullet = false;

        return bodyData;
    }


}