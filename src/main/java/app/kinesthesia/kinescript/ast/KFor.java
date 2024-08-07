package app.kinesthesia.kinescript.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KFor implements KStatement{

    private final int start;
    private final int end;
    private final String varName;

    List<KStatement> statements = new ArrayList<>();

    public KFor(int start, int end, List<KStatement> statements) {
        this("i", start, end, statements);
    }

    public KFor(String varName, int start,int end, List<KStatement> statements) {
        this.varName = varName;
        this.start = start;
        this.end = end;
        this.statements = statements;

    }
    @Override
    public Object execute(Map<String, Object> parentScope) {
        for (int i = start; i < end; i++) {
            for (KStatement statement : statements) {
                parentScope.put(varName, i);
                statement.execute(parentScope);
            }
        }
        return null;
    }
}
