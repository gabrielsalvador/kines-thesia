package me.gabrielsalvador.kinescript.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KFor implements KStatement{

    private final int start;
    private final int end;

    List<KStatement> statements = new ArrayList<>();

    public KFor(int start,int end, List<KStatement> statements) {
        this.start = start;
        this.end = end;
        this.statements = statements;

    }
    @Override
    public Object execute(Map<String, Object> parentScope) {
        for (int i = start; i < end; i++) {
            for (KStatement statement : statements) {
                statement.execute(parentScope);
            }
        }
        return null;
    }
}
