package me.gabrielsalvador.kinescript.ast;

import me.gabrielsalvador.kinescript.lang.Kinescript;
import org.w3c.dom.css.CSS2Properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KExpression implements KStatement {


    enum ExpressionType {
        VALUE,
        REFERENCE,
        OPERATION
    }

    //either has a value or a reference to a variable

    private Object value;
    private String reference;
    private ArrayList<KArg> args;// for function calls
    private ExpressionType type; // 0 for value , 1 for reference , 2 for operation

    private KExpression left;
    private KExpression right;
    private KOperator operator;


    public KExpression(int type, Object valueOrName) {
        this.type = ExpressionType.values()[type];
        if (this.type == ExpressionType.VALUE) {
            this.value = valueOrName;
        } else {
            this.reference = (String) valueOrName;
        }
    }


    public Object evaluate(Map<String, Object> scope) {
        if (type == ExpressionType.VALUE) {
            return value;
        } else if (type == ExpressionType.REFERENCE) {
            KStatement possibleFunction = Kinescript.getFunction(scope, reference, args);
            if (possibleFunction != null) {
                return possibleFunction.execute(scope);
            }
            Object var = scope.get(reference);
            if (var != null) {
                return var;
            } else {
                throw new RuntimeException("Variable or function" + reference + " not found");
            }
        } else if (type == ExpressionType.OPERATION) {
            if (left == null || right == null) {
                throw new RuntimeException("Operation not well formed");
            }
            Object leftValue = left.evaluate(scope);
            Object rightValue = right.evaluate(scope);

            return operator.apply(leftValue, rightValue);

        } else {
            return null;
        }
    }

    public void setArgs(ArrayList<KArg> args) {
        this.args = args;
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


    @Override
    public Object execute(Map<String, Object> parentScope) {
        return evaluate(parentScope);
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
}

