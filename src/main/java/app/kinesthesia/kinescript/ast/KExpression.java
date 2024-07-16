package app.kinesthesia.kinescript.ast;


import java.util.Map;

public abstract class KExpression  {
    enum ExpressionType {
        VALUE,
        REFERENCE,
        OPERATION,
        PROPERTY_ACCESS
    }

    protected ExpressionType type;

    public KExpression(int type) {
        this.type = ExpressionType.values()[type];
    }

    public KExpression(ExpressionType type) {
        this.type = type;
    }

    public abstract Object evaluate(Map<String, Object> scope);

//
//    public static KExpression propertyAccess( KExpression obj ,Object propertyName){
//        int type = ExpressionType.PROPERTY_ACCESS.ordinal();
//        KExpression expression =  new KExpression(type);
//        expression.left = obj;
//        if(propertyName instanceof String){
//            expression.reference = (String) propertyName;
//        }else if(propertyName instanceof Integer){
//            expression.propertyIndex = ((Integer) propertyName);
//        }
//        return expression;
//    }
//
//
//    //either has a value or a reference to a variable
//
//    private Object value;
//    private String reference;
//    private int propertyIndex;
//    private ArrayList<KArg> args;// for function calls
//
//    protected ExpressionType type; // 0 for value , 1 for reference , 2 for operation
//
//    private KExpression left;
//    private KExpression right;
//    private KOperator operator;
//

//
//    public KExpression(int type, Object valueOrName) {
//        this.type = ExpressionType.values()[type];
//        if (this.type == ExpressionType.VALUE) {
//            this.value = valueOrName;
//        } else {
//            this.reference = (String) valueOrName;
//        }
//    }
//
//
//    public Object evaluate(Map<String, Object> scope) {
//        Object result = null;
//
//        if (type == ExpressionType.VALUE) {
//            result = value;
//        } else if (type == ExpressionType.REFERENCE) {
//            KStatement possibleFunction = Kinescript.getFunction(scope, reference, args);
//            if (possibleFunction != null) {
//                result = possibleFunction.execute(scope);
//            } else {
//                Object var = scope.get(reference);
//                if (var != null) {
//                    if (var instanceof HasPProperties) {
//                        Object obj = ((KExpression) var).evaluate(scope);
//                        PObject pObject = (PObject) obj;
//                        result = pObject.getProperties();
//                    } else {
//                        result = var;
//                    }
//                } else {
//                    throw new RuntimeException("Variable or function " + reference + " not found");
//                }
//            }
//        } else if (type == ExpressionType.OPERATION) {
//            if (left == null || right == null) {
//                throw new RuntimeException("Operation not well formed");
//            }
//            Object leftValue = left.evaluate(scope);
//            Object rightValue = right.evaluate(scope);
//            result = operator.apply(leftValue, rightValue);
//        } else if (type == ExpressionType.PROPERTY_ACCESS) {
//            Object obj = left.evaluate(scope);
//            if (obj instanceof Map) {
//                Object value = ((Map<?, ?>) obj).get(reference);
//                if (value instanceof KExpression) {
//                    result = ((KExpression) value).evaluate(scope);
//                } else {
//                    result = value;
//                }
//            } else if (obj instanceof List) {
//                int index = propertyIndex;
//                if (index < 0) {
//                    throw new RuntimeException("Index must be positive");
//                }
//                if (index >= ((List<?>) obj).size()) {
//                    throw new RuntimeException("Index out of bounds");
//                }
//                result = ((List<?>) obj).get(index);
//            } else {
//                HashMap<String, Object> properties = new HashMap<>();
//                Class c = obj.getClass();
//                for (var field : c.getDeclaredFields()) {
//                    properties.put(field.getName(), field);
//                }
//                for (var method : c.getDeclaredMethods()) {
//                    properties.put(method.getName(), method);
//                }
//                result = properties.get(reference);
//            }
//        }
//
//        if(result instanceof PObject){
//            result = ((PObject) result).getProperties();
//            result = new KList((ArrayList<?>) result);
//        }
//        else if (result instanceof ArrayList<?>){
//            result = new KList((ArrayList<?>) result);
//        }
//
//        return result;
//    }
//
//    public KExpression setArgs(ArrayList<KArg> args) {
//        this.args = args;
//        return this;
//    }
//
//    public KExpression setLeft(KExpression left) {
//        this.left = left;
//        crunch();
//        return this;
//    }
//
//    public KExpression setRight(KExpression right) {
//        this.right = right;
//        crunch();
//        return this;
//    }
//
//    public KExpression setOperator(KOperator operator) {
//        this.operator = operator;
//        crunch();
//        return this;
//    }
//
//
//    @Override
//    public Object execute(Map<String, Object> parentScope) {
//        return evaluate(parentScope);
//    }
//
//
//    //if the expression is an operation and its operands are values, evaluate it and set the value
//    public void crunch() {
//        if (left != null && right != null && operator != null && left.type == ExpressionType.VALUE && right.type == ExpressionType.VALUE) {
//            value = operator.apply(left.evaluate(null), right.evaluate(null));
//            left = null;
//            right = null;
//            operator = null;
//            type = ExpressionType.VALUE;
//        }
//    }
//
//    @Override
//    public String toString() {
//        if (type == ExpressionType.VALUE) {
//            return value.toString();
//        } else if (type == ExpressionType.REFERENCE) {
//            return reference;
//        }
//        else {
//            return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
//        }
//    }

}

