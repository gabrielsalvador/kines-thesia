import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectPreset;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.pobject.components.musicalnote.MusicalNoteComponent;
import me.gabrielsalvador.utils.Interval;
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
        Interval interval = Interval.SECOND.octave(1);
        PObjectPreset preset = new PObjectPreset.ResonatorPreset(new Vec2(0, 0), new Vec2(10, 10), interval);
        PObject[] objs = preset.create();

        PObject resonator = objs[0];

        MusicalNoteComponent mnc = resonator.getComponent(MusicalNoteComponent.class);
        assertEquals(mnc.getInterval(), interval);




    }
}
