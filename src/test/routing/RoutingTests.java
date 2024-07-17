package routing;

import app.kinesthesia.core.Kinesthesia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoutingTests {
    //beforeAll

    Kinesthesia app;

    @BeforeAll
    void setup() {
        app = Kinesthesia.getInstance();



    }

    @Test
    void testRouting() {




    }
}
