import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.input.InputManager;
import me.gabrielsalvador.model.State;
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

        InputManager im = InputManager.getInstance();
        assert im != null && im instanceof InputManager;

        State s = State.getInstance();
        assert s != null && s instanceof State;

        ToolManager tm = ToolManager.getInstance();
        assert tm != null && tm instanceof ToolManager;

        
    }
}
