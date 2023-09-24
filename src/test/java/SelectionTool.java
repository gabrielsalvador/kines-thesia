import MockCanvas.MockCanvas;
import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.tools.SelectTool;
import me.gabrielsalvador.tools.ToolManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class SelectionTool {

    AppController appController;
    AppState appState;
    ToolManager toolManager;
    CountDownLatch latch;
    PObject obj;


    @BeforeEach
    public void setup() {
        appController = AppController.getInstance();
        appState = AppState.getInstance();
        toolManager = ToolManager.getInstance();
        toolManager.pushTool(SelectTool.class);
        latch = new CountDownLatch(1);


        AppController.getInstance().queueModification(() -> {
            obj = appController.createPObject();
            latch.countDown();
        });

        appController.applyModifications();
    }

    @Test
    public void selectObject() throws InterruptedException {
        latch.await();
        assert appState.getPObjects().size() == 1;
        MockCanvas mc = new MockCanvas();
        mc.updateHoveredObject(0,0);




    }
}
