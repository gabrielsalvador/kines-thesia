package routing;

import me.gabrielsalvador.core.AppController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
