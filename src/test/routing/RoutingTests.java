package routing;

import app.kinesthesia.core.AppController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoutingTests {
    //beforeAll

    AppController app;

    @BeforeAll
    void setup() {
        app = AppController.getInstance();



    }

    @Test
    void testRouting() {




    }
}
