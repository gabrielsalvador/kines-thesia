import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.core.BodyComponent;
import me.gabrielsalvador.core.MusicalNoteComponent;
import me.gabrielsalvador.core.PObject;
import me.gabrielsalvador.gui.processing.PObjectPreset;
import org.jbox2d.common.Vec2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PObjectPresetTests {
    static AppController app;

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


    @Test
    public void resonatorTest() {

        PObjectPreset preset = new PObjectPreset.ResonatorPreset(new Vec2(0, 0), new Vec2(10, 10), 2);
        PObject[] objs = preset.create();

        PObject resonator = objs[0];

        MusicalNoteComponent mnc = resonator.getComponent(MusicalNoteComponent.class);
        assertEquals(mnc.getInterval().interval, 2);




    }
}
