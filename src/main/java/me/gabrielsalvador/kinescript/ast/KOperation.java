package me.gabrielsalvador.kinescript.ast;

import java.util.Map;

public class KOperation extends KExpression {
    private KExpression left;
    private KExpression right;
    private KOperator operator;
    private Object value;

    public KOperation(KExpression left, KExpression right, KOperator operator) {
        super(ExpressionType.OPERATION);
        this.left = left;
        this.right = right;
        this.operator = operator;
        crunch();
    }


    public Object evaluate(Map<String, Object> scope) {

        if(type == ExpressionType.VALUE) return value;

        Object leftValue = left.evaluate(scope);
        Object rightValue = right.evaluate(scope);
        return operator.apply(leftValue, rightValue);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }

    //if the expression is an operation and its operands are values, evaluate it and set the value
    public void crunch() {
        if (left != null && right != null && operator != null && left.type == ExpressionType.VALUE && right.type == ExpressionType.VALUE) {
            value = operator.apply(left.evaluate(null), right.evaluate(null));
            left = null;
            right = null;
            operator = null;
            type = ExpressionType.VALUE;
        }
    }


    public KExpression setLeft(KExpression left) {
        this.left = left;
        crunch();
        return this;
    }

    public KExpression setRight(KExpression right) {
        this.right = right;
        crunch();
        return this;
    }

    public KExpression setOperator(KOperator operator) {
        this.operator = operator;
        crunch();
        return this;
    }
}