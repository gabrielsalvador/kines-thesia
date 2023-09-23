import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class SelectionToolTests {

    AppController appController;
    AppState appState;

    @BeforeEach
    public void setup() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        appController = AppController.getInstance();
        appState = AppState.getInstance();
        appController.createPObject().addComponent(BodyComponent.class);
    }

    @Test
    public void testSelectionTool() {

    }
}
