package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KAssign implements KStatement{

    private final String name;
    private final Object value;
    public KAssign(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object execute(Map<String, Object> parentScope) {
        parentScope.put(name, value);
        return value;
    }
}
