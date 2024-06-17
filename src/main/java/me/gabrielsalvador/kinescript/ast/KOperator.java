package me.gabrielsalvador.kinescript.ast;

public abstract class KOperator {


    public abstract Object apply(Object left, Object right);

    public static KOperator ADD = new KAdd();
    public static KOperator SUB = new KSUb();
    public static KOperator MUL = new KMul();
    public static KOperator DIV = new KDiv();
    public static class KAdd extends KOperator {

        @Override
        public Object apply(Object left, Object right) {
            if (left instanceof Number && right instanceof Number) {
                return ((Number) left).doubleValue() + ((Number) right).doubleValue();
            } else {
                throw new RuntimeException("Invalid operation");
            }
        }
    }

    public static class KSUb extends KOperator {

        @Override
        public Object apply(Object left, Object right) {
            if (left instanceof Number && right instanceof Number) {
                return ((Number) left).doubleValue() - ((Number) right).doubleValue();
            } else {
                throw new RuntimeException("Invalid operation");
            }
        }
    }

    public static class KMul extends KOperator {

        @Override
        public Object apply(Object left, Object right) {
            if (left instanceof Number && right instanceof Number) {
                return ((Number) left).doubleValue() * ((Number) right).doubleValue();
            } else {
                throw new RuntimeException("Invalid operation");
            }
        }
    }


    public static class KDiv extends KOperator {

        @Override
        public Object apply(Object left, Object right) {
            if (left instanceof Number && right instanceof Number) {
                return ((Number) left).doubleValue() / ((Number) right).doubleValue();
            } else {
                throw new RuntimeException("Invalid operation");
            }
        }
    }
}

