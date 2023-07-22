import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectProperty;
import me.gabrielsalvador.utils.Vector;

public class PObjectTests {
    
    @DisplayName("Creates a PObject")
    @Test
    public void testPObject() {
        PObject p = new PObject();
        assert p != null;
        Vector v = new Vector(1, 2);
        p.setPosition(new float[]{v.getX(), v.getY()});
        


        PObject child = new PObject();
        p.addChild(child);
        assert p.getChildren().contains(child);

        PObjectProperty<Integer> prop = new PObjectProperty<Integer>("mass");
        p.addProperty(prop);
        prop.setValue(10);
        assert  p.getProperty("mass") == prop;

        assert prop.getValue() == 10;
    }
}
