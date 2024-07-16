package app.kinesthesia.kinescript.ast;

import java.util.Map;

public class KPrint implements KStatement{

    private  KArg argument;

    public KPrint() {

    }

    public KPrint(KArg argument) {
        this.argument = argument;
    }

    public void setArgument(KArg argument) {
        this.argument = argument;
    }

    @Override
    public Object execute(Map<String, Object> parentScope) {
        System.out.println(this.argument.evaluate(parentScope));
        return null;
    }
}
