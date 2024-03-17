package me.gabrielsalvador.common;

import java.io.Serializable;

public interface SerializableRunnable extends  Serializable {

    void run(Object... a);

}
