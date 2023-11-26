import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectPreset;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PObjectPresetTests {
    static AppController app;
    static PObjectPreset pemitterPreset = new PObjectPreset.EmitterPreset();

    @BeforeAll
    public static void setUp() {
        app = AppController.getInstance();
    }

    @Test
    public void testPObjectPreset() {
        PObject obj = app.createPObject();
        app.addPObjectImmiadiately(obj);
        Assertions.assertNotNull(obj.getComponent(BodyComponent.class));
    }
}
