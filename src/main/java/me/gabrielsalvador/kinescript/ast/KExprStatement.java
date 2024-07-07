package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KExprStatement implements KStatement{

    private KExpression expression;

    public KExprStatement(KExpression expression) {
        this.expression = expression;
    }

    @Override
    public Object execute(Map<String, Object> parentScope) {
        return expression.evaluate(parentScope);
    }
}
