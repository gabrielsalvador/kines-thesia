package routing;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.pobject.PlayableNote;
import me.gabrielsalvador.pobject.components.Component;
import me.gabrielsalvador.pobject.components.RoutingComponent;
import me.gabrielsalvador.pobject.routing.PSocket;
import me.gabrielsalvador.pobject.routing.RoutingConnection;
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
        PSocket obj1 = new PSocket(new PlayableNote());
        PSocket obj2 = new PSocket(new PlayableNote());
        RoutingConnection c = new RoutingConnection(obj1, obj2);
        Component comp =  c.getComponent(RoutingComponent.class);
        List<?> props = comp.getProperties();
        //assert prop has one element
        assertEquals(1, props.size());


    }
}
