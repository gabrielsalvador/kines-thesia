import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.view.ViewManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneralTests {


    @DisplayName("Runs without crashing")
    @Test()
    public void testMain() {
        Sinesthesia.main(new String[] {});
    }

    @DisplayName("singletons are get and set correctly")
    @Test()
    public void singletons() {
        ViewManager vm = ViewManager.getInstance();
        //assert vm is not null and is an instance of ViewManager
        assert vm != null && vm instanceof ViewManager;
    }
}
