package me.gabrielsalvador.kinescript.builtins;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.kinescript.ast.KArg;
import me.gabrielsalvador.kinescript.ast.KStatement;
import me.gabrielsalvador.pobject.PObject;

import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.utils.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AddBuiltin implements KStatement {

    private ArrayList<KArg> args;

    public AddBuiltin(ArrayList<KArg> args) {
        this.args = args;
    }

    @Override
    public Future<PObject> execute(Map<String, Object> parentScope) {
        String presetName = (String) args.get(0).evaluate(parentScope);
        float x = ((Number) args.get(1).evaluate(parentScope)).floatValue();
        float y = ((Number) args.get(2).evaluate(parentScope)).floatValue();

        AppController app = AppController.getInstance();

        CompletableFuture<PObject> futureObject = new CompletableFuture<>();

        app.queueModification(() -> {

        });

        return futureObject;
    }
}