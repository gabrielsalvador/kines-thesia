package app.kinesthesia.core;





import app.kinesthesia.kinescript.ast.KFunction;

import java.io.Serializable;

public class CallbackWrapper implements Serializable {
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
