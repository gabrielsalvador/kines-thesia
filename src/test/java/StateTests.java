import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.gabrielsalvador.model.PObject;
import me.gabrielsalvador.model.AppState;

public class StateTests {

    @DisplayName("adds a pobject")
    @Test
    public void addsAPObject() {
        AppState s = AppState.getInstance();
        PObject p = new PObject();
        s.addPObject(p);
        assert s.getPObjects().contains(p);
    }
}
