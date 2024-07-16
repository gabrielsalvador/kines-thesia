import app.kinesthesia.core.MathUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathTests {

    @Test
    public void isPointOnLineTest() {

        boolean result = MathUtils.isPointOverLineSegment(1,10,0,0,10,0,10);

        assertTrue(result);
    }
}
