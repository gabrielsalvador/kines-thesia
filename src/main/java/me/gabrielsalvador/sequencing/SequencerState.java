package me.gabrielsalvador.sequencing;

import java.io.Serial;
import java.io.Serializable;

public class SequencerState implements Serializable {

    private boolean[][] _steps = new boolean[SequencerController.DIVISION_TIME][SequencerController.DIVISION_PITCH];




    public boolean[][] getSteps() {
        return _steps;
    }

    public void setSteps(boolean[][] steps) {
        _steps = steps;
    }
    public void setStep(int x, int y, boolean value) {
        _steps[x][y] = value;
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        _steps = (boolean[][]) in.readObject();
    }
}
