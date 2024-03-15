package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KPrint implements KStatement{

    private Object value;

    public KPrint(Object value) {
        this.value = value;
    }
    @Override
    public Object execute(Map<String, Object> parentScope) {
        System.out.println(this.value);
        return value;
    }
}
