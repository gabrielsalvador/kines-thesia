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


public class CanvasControllerTests {
    static ControlP5 mockCp5;
    static int mouseX = 0;
    static int mouseY = 0;

    static CanvasController canvasController;
    static PObject obj;

    public void simulateMove(int x, int y) {
        mouseX = x;
        mouseY = y;
        canvasController.onMove();
    }

    @BeforeEach
    public  void setUp() throws NoSuchFieldException, IllegalAccessException {

        mockCp5 = mock(ControlP5.class);

        // Assuming mouseX and mouseY are defined somewhere in your code
        when(mockCp5.getPointer()).thenAnswer(invocation -> {
            ControlWindowPointer pVector = mock(ControlWindowPointer.class);  // Create a mock PVector
            when(pVector.getX()).thenReturn(mouseX);  // Return your desired value for getX()
            when(pVector.getY()).thenReturn(mouseY);  // Return your desired value for getY()
            return pVector;  // Return the mocked PVector
        });


        //mock ToolManager
        ToolManager tm = mock(ToolManager.class);
        when(tm.getCurrentTool()).thenReturn(mock(SelectTool.class));
        // Use reflection to inject the mock into the singleton
        Field instanceField = ToolManager.class.getDeclaredField("_instance");  // Assuming "_instance" is the name of your singleton instance field
        instanceField.setAccessible(true);
        instanceField.set(null, tm);




        canvasController = new CanvasController(mockCp5, "CanvasController");
        AppController app = AppController.getInstance();
        obj = app.createPObject();
        app.addPObjectImmiadiately(obj);




    }

    @Test
    public void selectsHologramBody() {
        BodyComponent objBody = obj.getBodyComponent();
        objBody.setPixelPosition(new Vec2(50,50));

        simulateMove(5,5);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(50,50);
        assertEquals(obj,canvasController.getCurrentlyHovering());

    }

    @Test void selectsCircularPhysicsBody(){
        BodyData bodyData = BodyData.getDefaultBodyData();

        PhysicsBodyComponent objBody = new PhysicsBodyComponent(obj,bodyData);
        obj.addComponent(BodyComponent.class,objBody);

        simulateMove(11,11);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(10,10);
        assertEquals(obj,canvasController.getCurrentlyHovering());

    }

    @Test void selectsBoxPhysicsBody(){
        BodyData bodyData = BodyData.getDefaultBodyData();
        bodyData.shapeType = ShapeType.POLYGON;
        bodyData.vertices = PhysicsManager.getInstance().coordPixelsToWorld(new Vec2[]{
                new Vec2(500,500),
                new Vec2(500,600),
                new Vec2(600,600),
                new Vec2(600,500)
        });



        PhysicsBodyComponent objBody = new PhysicsBodyComponent(obj,bodyData);
        obj.addComponent(BodyComponent.class,objBody);

        simulateMove(550,499);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(550,601);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(601,601);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(550,550);
        assertEquals(obj,canvasController.getCurrentlyHovering());



    }


}
