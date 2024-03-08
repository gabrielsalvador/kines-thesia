package me.gabrielsalvador.common;

import java.io.Serializable;

public interface SerializableRunnable extends  Serializable {

    public abstract void run(Object ...a);

}
