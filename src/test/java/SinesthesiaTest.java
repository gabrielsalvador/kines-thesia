import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.core.StateManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinesthesiaTest {


    @DisplayName("Runs without crashing")
    @Test()
    public void testMain() {
        Sinesthesia.main(new String[] {});
    }

    @DisplayName("globals are set and retrieved")
    @Test()
    public void testGlobals() {
        Sinesthesia s = new Sinesthesia();
        StateManager manager = s.getStateManager();
        assert manager == s.get(StateManager.class);
    }
}
