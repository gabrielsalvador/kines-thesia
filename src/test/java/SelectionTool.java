import MockCanvas.MockCanvas;
import controlP5.ControlP5;
import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import me.gabrielsalvador.tools.SelectTool;
import me.gabrielsalvador.tools.ToolManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class SelectionTool {

    AppController appController;
    AppState appState;
    ToolManager toolManager;
    CountDownLatch latch;
    PObject obj;
    MockCanvas mc ;

    @BeforeEach
    public void setup() {
        appController = AppController.getInstance();
        appState = AppState.getInstance();
        toolManager = ToolManager.getInstance();
        toolManager.pushTool(SelectTool.class);
        latch = new CountDownLatch(1);
        mc = new MockCanvas();

        AppController.getInstance().queueModification(() -> {
            obj = appController.createPObject().addComponent(BodyComponent.class, new HologramBody( new PObject()));
            latch.countDown();
        });

        appController.applyModifications();
    }

    @Test
    public void selectObject() throws InterruptedException {
        latch.await();
        Assertions.assertEquals(1, appState.getPObjects().size());

        mc.updateHoveredObject(0,0);
        PObject hovering = mc.getHoveringObject();
        Assertions.assertEquals(obj, hovering);



    }
}
