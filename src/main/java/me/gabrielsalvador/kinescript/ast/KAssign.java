package me.gabrielsalvador.kinescript.ast;

import java.util.Map;
import java.util.concurrent.Future;

public class KAssign implements KStatement{

    private final String name;
    private final Object value;
    public KAssign(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object execute(Map<String, Object> parentScope) {
        if(value instanceof KFunction) { // it needs to be executed
            parentScope.put(name, value);
            return value;
        }
        else if (value instanceof KExpression){
            Object result = ((KExpression) value).evaluate(parentScope);
            if (result instanceof Future){ // wait for the future to complete
                try {
                    parentScope.put(name, ((Future) result).get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                parentScope.put(name, result);
            }
            return parentScope.get(name);
        }
        else{
            parentScope.put(name, value);
            return value;
        }


    }
}
