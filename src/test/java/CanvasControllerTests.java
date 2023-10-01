import controlP5.ControlP5;
import controlP5.ControlWindow;
import controlP5.Pointer;
import me.gabrielsalvador.core.CanvasController;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class CanvasControllerTests {
    static ControlP5 mockCp5;
    static int mouseX = 0;
    static int mouseY = 0;

    static CanvasController canvasController;


    @BeforeAll
    public static void setUp() {

        mockCp5 = mock(ControlP5.class);

        canvasController = new CanvasController(mockCp5, "CanvasController");
        when(mockCp5.getPointer()).thenReturn(  );








    }

    @Test
    public void shouldSelectPObject() {
        canvasController.onMove();
        canvasController.onPress();


    }
}
