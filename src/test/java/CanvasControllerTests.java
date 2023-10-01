import controlP5.ControlP5;
import controlP5.ControllerInterface;
import me.gabrielsalvador.core.CanvasController;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;


public class CanvasControllerTests {
    private ControlP5 mockCp5;



    public void setUp() {

        mockCp5 = mock(ControlP5.class);
        CanvasController canvasController = new CanvasController(mockCp5, "CanvasController");

        canvasController.mousePressed();


    }

}
