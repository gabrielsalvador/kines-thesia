package me.gabrielsalvador.kinescript.builtins;

import me.gabrielsalvador.core.AppController;
import me.gabrielsalvador.kinescript.ast.KArg;
import me.gabrielsalvador.kinescript.ast.KStatement;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.pobject.PObjectPreset;
import me.gabrielsalvador.pobject.components.body.BodyComponent;
import me.gabrielsalvador.utils.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class AddBuiltin implements KStatement {

    private ArrayList<KArg> args;
    public AddBuiltin(ArrayList<KArg> args) {
        this.args = args;
    }

    @Override
    public Object execute(Map<String, Object> parentScope) {
        String presetName = (String) args.get(0).evaluate(parentScope);
        float x = ((Number) args.get(1).evaluate(parentScope)).floatValue();
        float y = ((Number) args.get(2).evaluate(parentScope)).floatValue();

        AppController app = AppController.getInstance();

        app.queueModification(() -> {
            PObjectPreset preset = PObjectPreset.getPresetByName(presetName);
            PObject[] objects = preset.create();
            for (PObject object : objects) {
                BodyComponent body = object.getBodyComponent();
                body.setPixelPosition(new org.jbox2d.common.Vec2(x, y));
                app.addPObjectImmiadiately(object);

            }
        });


        return null;
    }
}
