package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KPrint implements KStatement{

    private KArg argument;

    public KPrint(Object value) {
        this.argument = (KArg) value;
    }
    @Override
    public Object execute(Map<String, Object> parentScope) {
        System.out.println(this.argument.evaluate(parentScope));
        return argument;
    }
}
