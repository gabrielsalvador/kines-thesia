import me.gabrielsalvador.model.PObjectProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PObjectPropertyTest {
    @Test
    public void testSetName() {
        PObjectProperty<String> property = new PObjectProperty<>("testProperty");
        assertEquals("testProperty", property.getName());
    }

    @Test
    public void testSetValue() {
        PObjectProperty<String> property = new PObjectProperty<>("testProperty");
        property.setValue("testValue");
        assertEquals("testValue", property.getValue());

        PObjectProperty<Integer> property2 = new PObjectProperty<>("testProperty");
        property2.setValue(2);
        assertEquals("testValue", property.getValue());
    }

    @Test
    public void testGetValue() {
        PObjectProperty<String> property = new PObjectProperty<>("testProperty");
        property.setValue("testValue");
        assertEquals("testValue", property.getValue());
    }

    @Test
    public void testGetPayloadType() {
        PObjectProperty<String> property = new PObjectProperty<>("testProperty");
        property.setValue("testValue");
        assertEquals(String.class, property.getValueType());
    }

    @Test
    public void testSetValueReturn() {
        PObjectProperty<String> property = new PObjectProperty<>("testProperty");
        assertEquals(property, property.setValue("testValue"));
    }

    @Test
    public void testGetValueCast() {
        PObjectProperty<Integer> property = new PObjectProperty<>("testProperty");
        property.setValue(123);
        assertTrue(property.getValue() instanceof Integer);
    }

    @Test
    public void testGetValueAfterCast() {
        PObjectProperty<Integer> property = new PObjectProperty<>("testProperty");
        property.setValue(123);
        assertEquals(123, property.getValue());
    }
}



