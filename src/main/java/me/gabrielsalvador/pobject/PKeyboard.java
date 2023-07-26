package me.gabrielsalvador.pobject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;

public class PKeyboard extends PObject{


    public PKeyboard() {
        super();
        setView(new PKeyboardView(this));

    }


    @Serial
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        // default deserialization
        aInputStream.defaultReadObject();

        setView(new PKeyboardView(this));

    }
}
