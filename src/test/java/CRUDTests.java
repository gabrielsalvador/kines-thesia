import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.body.HologramBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.concurrent.CountDownLatch;

public class CRUDTests {

    AppController appController;
    AppState appState;
    CountDownLatch latch;

    @BeforeEach
    public void setup() throws Exception {
        appController = AppController.getInstance();
        appState = AppState.getInstance();
        PObject obj = appController.createPObject();
        obj.addComponent(BodyComponent.class, new HologramBody(obj));
        latch = new CountDownLatch(1); // set the count to 1

        AppController.getInstance().queueModification(() -> {
            AppController.getInstance().removePObjectImmiadiately(obj);
            latch.countDown(); // release the latch
        });
        //apply the modification
        appController.applyModifications();
    }

    @Test
    public void deletesAnObject() throws InterruptedException {

        latch.await(); // this will block until latch.countDown() is called

        int size = AppState.getInstance().getPObjects().size();
        assert size == 0;
    }
}
