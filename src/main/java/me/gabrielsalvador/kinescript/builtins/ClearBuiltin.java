package me.gabrielsalvador.kinescript.builtins;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.kinescript.ast.KArg;
import me.gabrielsalvador.kinescript.ast.KStatement;

import java.util.List;
import java.util.Map;

public class ClearBuiltin implements KStatement {

    private List<KArg> args;

    public ClearBuiltin(List<KArg> args) {
        this.args = args;
    }

    @Override
    public Object execute(Map<String, Object> parentScope) {

        AppController app = AppController.getInstance();
        app.clearObjects();

        return null;

    }
}
