package me.gabrielsalvador.kinescript.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KProgram implements KStatement{

    ArrayList<KStatement> statements;
    HashMap<String, Object> scope = new HashMap<>();

    public KProgram(ArrayList<KStatement> statements) {
        this.statements = statements;
    }
    @Override
    public Object execute(Map<String, Object> parentScope) {
        for (KStatement statement : statements) {
            statement.execute(scope);
        }
        return null;
    }

    public Map<String, Object> getScope() {
        return scope;
    }

}
