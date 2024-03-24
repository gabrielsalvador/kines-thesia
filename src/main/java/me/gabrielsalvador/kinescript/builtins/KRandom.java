package me.gabrielsalvador.kinescript.builtins;

import me.gabrielsalvador.kinescript.ast.KStatement;

import java.util.Map;

public class KRandom implements KStatement {

    @Override
    public Object execute(Map<String, Object> parentScope) {
        int range = 10;
        return (int) Math.round(Math.random() * range);
    }
}
