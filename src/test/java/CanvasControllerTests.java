import controlP5.ControlP5;
import controlP5.ControlWindowPointer;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.CanvasController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import org.jbox2d.common.Vec2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


public class CanvasControllerTests {
    static ControlP5 mockCp5;
    static int mouseX = 0;
    static int mouseY = 0;

    static CanvasController canvasController;
    static PObject obj;

    @BeforeAll
    public static void setUp() {

        mockCp5 = mock(ControlP5.class);

        // Assuming mouseX and mouseY are defined somewhere in your code
        when(mockCp5.getPointer()).thenAnswer(invocation -> {
            ControlWindowPointer pVector = mock(ControlWindowPointer.class);  // Create a mock PVector
            when(pVector.getX()).thenReturn(mouseX);  // Return your desired value for getX()
            when(pVector.getY()).thenReturn(mouseY);  // Return your desired value for getY()
            return pVector;  // Return the mocked PVector
        });

        canvasController = new CanvasController(mockCp5, "CanvasController");
        obj = AppController.getInstance().createPObject();
        BodyComponent objBody = obj.getBodyComponent();
        objBody.setPixelPosition(new Vec2(50,50));


    }

    @Test
    public void shouldSelectPObject() {

        simulateMove(5,5);
        assertNull(canvasController.getCurrentlyHovering());

        simulateMove(50,50);
        assertEquals(obj,canvasController.getCurrentlyHovering());

    }


    public void simulateMove(int x, int y) {
        mouseX = x;
        mouseY = y;
        canvasController.onMove();
    }
}
