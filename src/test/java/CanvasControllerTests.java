import controlP5.ControlP5;
import controlP5.ControlWindowPointer;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PhysicsManager;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.BodyData;
import me.gabrielsalvador.pobject.components.body.PhysicsBodyComponent;
import me.gabrielsalvador.tools.SelectTool;
import me.gabrielsalvador.tools.ToolManager;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CanvasControllerTests {
    static ControlP5 mockCp5;
    static int mouseX = 0;
    static int mouseY = 0;
    static CanvasController canvasController;
    static PObject obj;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        mockCp5 = mock(ControlP5.class);
        when(mockCp5.getPointer()).thenAnswer(invocation -> {
            ControlWindowPointer pVector = mock(ControlWindowPointer.class);
            when(pVector.getX()).thenReturn(mouseX);
            when(pVector.getY()).thenReturn(mouseY);
            return pVector;
        });

        // Mock ToolManager
        ToolManager tm = mock(ToolManager.class);
        when(tm.getCurrentTool()).thenReturn(mock(SelectTool.class));
        injectSingleton(tm, ToolManager.class, "_instance");

        canvasController = new CanvasController(mockCp5, "CanvasController");
        AppController app = AppController.getInstance();
        obj = app.createPObject();
        app.addPObjectImmiadiately(obj);
    }

    @Test
    public void selectsHologramBody() {
        BodyComponent objBody = obj.getBodyComponent();
        objBody.setPixelPosition(new Vec2(50, 50));

        simulateMove(5, 5);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(50, 50);
        assertEquals(obj, canvasController.getCurrentlyHovering());
    }

    @Test
    public void selectsCircularPhysicsBody() {
        BodyData bodyData = BodyData.getDefaultBodyData();
        PhysicsBodyComponent objBody = new PhysicsBodyComponent(obj, bodyData);
        obj.addComponent(BodyComponent.class, objBody);

        simulateMove(11, 11);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(10, 10);
        assertEquals(obj, canvasController.getCurrentlyHovering());
    }

    @Test
    public void selectsBoxPhysicsBody() {
        BodyData bodyData = BodyData.getDefaultBodyData();
        bodyData.shapeType = ShapeType.POLYGON;
        bodyData.vertices = PhysicsManager.getInstance().coordPixelsToWorld(new Vec2[]{
                new Vec2(-500, -500),
                new Vec2(500, -500),
                new Vec2(500, 500),
                new Vec2(-500, 500)
        });

        PhysicsBodyComponent objBody = new PhysicsBodyComponent(obj, bodyData);
        obj.addComponent(BodyComponent.class, objBody);
        Body body = objBody.getJBox2DBody();
        //float angle = (float) Math.toRadians(45);


        for(float angle = 0; angle < 360; angle += 1){
            System.out.println(angle);
            body.setTransform(body.getPosition(),angle); // 45-degree rotation



            simulateMove(10, 10);
            //simulatePress(550,550);
            assertEquals(obj, canvasController.getCurrentlyHovering());
        }

    }

    private void simulatePress(int i, int i1) {
        mouseX = i;
        mouseY = i1;
        canvasController.onPress();
    }

    private void simulateMove(int x, int y) {
        mouseX = x;
        mouseY = y;
        canvasController.onMove();
    }

    private static <T> void injectSingleton(T mockInstance, Class<T> clazz, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = clazz.getDeclaredField(fieldName);
        instanceField.setAccessible(true);
        instanceField.set(null, mockInstance);
    }
}
