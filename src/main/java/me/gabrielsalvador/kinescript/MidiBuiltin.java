package me.gabrielsalvador.kinescript;

import me.gabrielsalvador.kinescript.ast.KArg;
import me.gabrielsalvador.kinescript.ast.KStatement;
import me.gabrielsalvador.midi.MidiManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MidiBuiltin implements KStatement {

    List<KArg> args;
    public MidiBuiltin(ArrayList<KArg> args) {
        this.args = args;
    }

    @Override
    public Object execute(Map<String, Object> parentScope) {
        int channel = (int) args.get(0).evaluate(parentScope);
        int pitch = (int) args.get(1).evaluate(parentScope);
        int velocity = (int) args.get(2).evaluate(parentScope);
        MidiManager.getInstance().sendNote(channel, pitch, velocity);
        return null;
    }
}
