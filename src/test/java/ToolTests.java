import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.gabrielsalvador.tools.AddTool;
import me.gabrielsalvador.tools.Tool;
import me.gabrielsalvador.tools.ToolManager;

public class ToolTests {


    @DisplayName("Creates a tool")
    @Test
    public void testTool() {
        Tool tool =  new AddTool();
        assert tool != null;
    }
    

    @DisplayName("sets current tool")
    @Test
    public void testSetCurrentTool() {
        ToolManager tm = ToolManager.getInstance();
        Tool tool1 =  new AddTool();
        Tool tool2 =  new AddTool();
        tm.setCurrentTool(tool1);
        assert tm.get_currentTool() == tool1;
        tm.setCurrentTool(tool2);
        assert tm.get_currentTool() == tool2;

    }
}
