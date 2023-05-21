import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.gabrielsalvador.model.PObject;
import me.gabrielsalvador.model.State;

public class StateTests {

    @DisplayName("adds a pobject")
    @Test
    public void addsAPObject() {
        State s = State.getInstance();
        PObject p = new PObject();
        s.addPObject(p);
        assert s.getPObjects().contains(p);
    }
}
