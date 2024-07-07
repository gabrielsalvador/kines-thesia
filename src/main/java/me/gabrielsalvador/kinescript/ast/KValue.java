package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KValue extends KExpression {
    private Object value;

    public KValue(Object value) {
        super(ExpressionType.VALUE);
        this.value = value;
    }

    @Override
    public Object evaluate(Map<String, Object> scope) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}