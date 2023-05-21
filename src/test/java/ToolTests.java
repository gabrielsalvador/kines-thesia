import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.gabrielsalvador.tool.Tool;
import me.gabrielsalvador.tool.ToolManager;
import me.gabrielsalvador.tool.AddTool;

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
        assert tm.getCurrentTool() == tool1;
        tm.setCurrentTool(tool2);
        assert tm.getCurrentTool() == tool2;

    }
}
