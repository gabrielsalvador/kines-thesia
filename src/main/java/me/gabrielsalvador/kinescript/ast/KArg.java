package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KArg {

    //either has a value or a reference to a variable

    private Object value;
    private String reference;
   private boolean type; // 0 for value , 1 for reference


    public KArg(boolean type, Object valueOrName) {
        this.type = type;
        if(!type) {
            this.value = valueOrName;
        } else {
            this.reference = (String) valueOrName;
        }
    }


    public Object evaluate(Map<String, Object> scope) {
        if(type) {
            return scope.get(reference);
        } else {
            return value;
        }
    }
}
