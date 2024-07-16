package app.kinesthesia.kinescript.ast;

import java.util.Map;

public interface KStatement {

    Object execute(Map<String, Object> parentScope);
}
