package me.gabrielsalvador.kinescript.ast;

import me.gabrielsalvador.kinescript.lang.Kinescript;

import java.util.Map;

public class KExpression  {

    //either has a value or a reference to a variable

    private Object value;
    private String reference;
   private final boolean isReference; // 0 for value , 1 for reference


    public KExpression(boolean type, Object valueOrName) {
        this.isReference = type;
        if(!type) {
            this.value = valueOrName;
        } else {
            this.reference = (String) valueOrName;
        }
    }


    public Object evaluate(Map<String, Object> scope) {
        if(isReference) {
            KStatement possibleFunction = Kinescript.getFunction(scope,reference);
            if(possibleFunction != null) {
                return possibleFunction.execute(scope);
            }
            Object var =  scope.get(reference);
            if (var != null) {
                return var;
            } else {
                throw new RuntimeException("Variable " + reference + " not found");
            }
        } else {
            return value;
        }
    }
}
