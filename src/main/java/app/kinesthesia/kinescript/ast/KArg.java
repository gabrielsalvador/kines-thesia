package app.kinesthesia.kinescript.ast;

public class KArg {
    private final KExpression expression;

    public KArg(KExpression exp) {
        this.expression = (KExpression) exp;
    }

    public Object evaluate(java.util.Map<String, Object> scope) {
        return this.expression.evaluate(scope);
    }
}
