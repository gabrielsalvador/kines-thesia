package app.kinesthesia.gui.processing;

import java.io.Serializable;

public interface SerializableRunnable extends  Serializable {

    void run(Object... a);

}
