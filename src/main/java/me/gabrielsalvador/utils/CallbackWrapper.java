package me.gabrielsalvador.utils;

import me.gabrielsalvador.kinescript.ast.KFunction;

public class CallbackWrapper {
    private KFunction kFunction;
    private Runnable runnable;

    public CallbackWrapper(KFunction kFunction) {
        this.kFunction = kFunction;
    }

    public CallbackWrapper(Runnable runnable) {
        this.runnable = runnable;
    }

    public boolean isKFunction() {
        return kFunction != null;
    }

    public boolean isRunnable() {
        return runnable != null;
    }

    public KFunction getKFunction() {
        return kFunction;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
