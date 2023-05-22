import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.input.AppController;
import me.gabrielsalvador.model.AppState;
import me.gabrielsalvador.tools.ToolManager;
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
        assert vm != null && vm instanceof ViewManager;

        AppController im = AppController.getInstance();
        assert im != null && im instanceof AppController;

        AppState s = AppState.getInstance();
        assert s != null && s instanceof AppState;

        ToolManager tm = ToolManager.getInstance();
        assert tm != null && tm instanceof ToolManager;
    }
}
