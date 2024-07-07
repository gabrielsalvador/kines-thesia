package me.gabrielsalvador.kinescript.ast;

import me.gabrielsalvador.kinescript.lang.Kinescript;

import java.util.ArrayList;
import java.util.Map;

public class KReference extends KExpression {
    private String reference;
    private ArrayList<KArg> args;

    public KReference(String reference) {
        super(ExpressionType.REFERENCE);
        this.reference = reference;
    }

    public KReference(String reference, ArrayList<KArg> args) {
        super(ExpressionType.REFERENCE);
        this.reference = reference;
        this.args = args;
    }

    @Override
    public Object evaluate(Map<String, Object> scope) {
        KStatement possibleFunction = Kinescript.getFunction(scope, reference, args);
        if (possibleFunction != null) {
            return possibleFunction.execute(scope);
        } else {
            Object var = scope.get(reference);
            if (var != null) {
                return var;
            } else {
                throw new RuntimeException("Variable or function " + reference + " not found");
            }
        }
    }

    @Override
    public String toString() {
        return reference;
    }
}