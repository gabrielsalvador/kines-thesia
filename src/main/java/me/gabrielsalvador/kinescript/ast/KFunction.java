package me.gabrielsalvador.kinescript.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KFunction implements KStatement{


    int parameterNumber;
    private Map<String, Object> scope = new HashMap<>();
    private List<KStatement> statements = new ArrayList<>();
    private String sourceCode;

    public KFunction( int parameterNumber, List<KStatement> statements) {

        this.parameterNumber = parameterNumber;
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

    public int getParameterNumber() {
        return parameterNumber;
    }

    public List<KStatement> getStatements() {
        return new ArrayList<>(statements);
    }

    public String getSourceCode() {
        return sourceCode;
    }
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
