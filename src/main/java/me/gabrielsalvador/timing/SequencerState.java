package me.gabrielsalvador.timing;

import java.io.Serial;
import java.io.Serializable;

public class SequencerState implements Serializable {

    private boolean[][] _steps = new boolean[SequencerController.MAX_DIVISION_TIME][SequencerController.MAX_DIVISION_PITCH];




    public boolean[][] getSteps() {
        return _steps;
    }

    public void setSteps(boolean[][] steps) {
        _steps = steps;
    }
    public void setStep(int x, int y, boolean value) {
        _steps[x][y] = value;
    }

    public void clear() {
        for (int x = 0; x < _steps.length; x++) {
            for (int y = 0; y < _steps[x].length; y++) {
                _steps[x][y] = false;
            }
        }
    }
    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        _steps = (boolean[][]) in.readObject();
    }
}
