package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KArg {

    //either has a value or a reference to a variable

    private Object value;
    private String reference;
   private boolean isReference; // 0 for value , 1 for reference


    public KArg(boolean type, Object valueOrName) {
        this.isReference = type;
        if(!type) {
            this.value = valueOrName;
        } else {
            this.reference = (String) valueOrName;
        }
    }


    public Object evaluate(Map<String, Object> scope) {
        if(isReference) {
            return scope.get(reference);
        } else {
            return value;
        }
    }
}
