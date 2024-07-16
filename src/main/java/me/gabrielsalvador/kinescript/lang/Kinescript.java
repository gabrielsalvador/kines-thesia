package me.gabrielsalvador.kinescript.lang;

import me.gabrielsalvador.kinescript.ast.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Kinescript implements KinescriptVisitor {

    private KFunction program;

    private static final HashMap<String, KStatement> builtInFunctions = new HashMap<>();

    public static KFunction compileFunction(String code) {

        KinescriptLexer lexer = new KinescriptLexer(new org.antlr.v4.runtime.ANTLRInputStream(code));
        KinescriptParser parser = new KinescriptParser(new org.antlr.v4.runtime.CommonTokenStream(lexer));
        Kinescript kinescript = new Kinescript();
        KFunction func = (KFunction) kinescript.visitProgram(parser.program());
        func.setSourceCode(code);
        return func;

    }

    @Override
    public Object visitProgram(KinescriptParser.ProgramContext ctx) {

        ArrayList statements = new ArrayList();
        program = new KFunction(0, statements);

        for (KinescriptParser.StatementContext statement : ctx.statement()) {
            statements.add(visitStatement(statement));
        }


        return program;
    }

    @Override
    public Object visitStatement(KinescriptParser.StatementContext ctx) {
        if (ctx.definition() != null) {
            return visitDefinition(ctx.definition());
        } else if (ctx.assignment() != null) {
            return visitAssignment(ctx.assignment());
        } else if (ctx.invocation() != null) {
            return visitInvocation(ctx.invocation());
        } else if (ctx.expr() != null) {
            //return a statement that is an expression
            return new KExprStatement(visitExpr(ctx.expr()));
        } else if (ctx.for_() != null) {
            return visitFor(ctx.for_());
        } else {
            throw new RuntimeException("Unknown statement type");
        }
    }


    @Override
    public Object visitAssignment(KinescriptParser.AssignmentContext ctx) {
        String name = ctx.ID().getText();

        Object value = null;
        if (ctx.expr() != null) {
            value = visitExpr(ctx.expr());
        } else if (ctx.definition() != null) {
            value = visitDefinition(ctx.definition());
        }


        //the variable is added to the scope at compile because it will be used deeper in the AST for compilation
        program.getScope().put(name, value);

        return new KAssign(name, value);

    }


    @Override
    public Object visitDefinition(KinescriptParser.DefinitionContext ctx) {
        int parameterNumber = 0;
        if (ctx.args() != null) {
            List args = (List) visitArgs(ctx.args());
            parameterNumber = args.size();
        }

        List<KStatement> statements = new ArrayList<>();
        for (KinescriptParser.StatementContext statement : ctx.statement()) {
            statements.add((KStatement) visitStatement(statement));
        }

        return new KFunction(parameterNumber, statements);
    }



    public KExpression visitExpr(ParseTree expr) {

        if (expr instanceof KinescriptParser.OperationExpressionContext) {
            return (KExpression) visitOperationExpression((KinescriptParser.OperationExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.IntExpressionContext) {
            return (KExpression) visitIntExpression((KinescriptParser.IntExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.IdExpressionContext) {
            return (KExpression) visitIdExpression((KinescriptParser.IdExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.StringExpressionContext) {
            return (KExpression) visitStringExpression((KinescriptParser.StringExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.PropertyDotExpressionContext) {
            return (KExpression) visitPropertyDotExpression((KinescriptParser.PropertyDotExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.PropertyIndexExpressionContext) {
            return (KExpression) visitPropertyIndexExpression((KinescriptParser.PropertyIndexExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.InvocationExpressionContext) {
            return (KExpression) visitInvocationExpression((KinescriptParser.InvocationExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.ParenExpressionContext) {
            return (KExpression) visitParenExpression((KinescriptParser.ParenExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.NegateExpressionContext) {
            return (KExpression) visitNegateExpression((KinescriptParser.NegateExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.RangeExpressionContext) {
            return (KExpression) visitRangeExpression((KinescriptParser.RangeExpressionContext) expr);
        }else if (expr instanceof KinescriptParser.ObjectExpressionContext) {
            return (KExpression) visitObjectExpression((KinescriptParser.ObjectExpressionContext) expr);
        }


        else {
            throw new RuntimeException("Unknown expression type");
        }
    }

    @Override
    public Object visitNegateExpression(KinescriptParser.NegateExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitOperationExpression(KinescriptParser.OperationExpressionContext ctx) {
        KExpression left = (KExpression) visitExpr(ctx.expr(0));
        KExpression right = (KExpression) visitExpr(ctx.expr(1));
        String[] whichOperator = new String[]{
                ctx.PLUS() != null ? ctx.PLUS().getText() : null,
                ctx.STAR() != null ? ctx.STAR().getText() : null,
                ctx.MINUS() != null ? ctx.MINUS().getText() : null,
                ctx.DIV() != null ? ctx.DIV().getText() : null
        };

        KOperator operator = getOperator(whichOperator);
        return new KOperation( left, right,operator);
    }


    @Override
    public Object visitIntExpression(KinescriptParser.IntExpressionContext ctx) {
        return new KValue(Integer.parseInt(ctx.INT().getText()));
//        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Object visitStringExpression(KinescriptParser.StringExpressionContext ctx) {
        return new KValue(ctx.STRING().getText());
    }

    @Override
    public Object visitIdExpression(KinescriptParser.IdExpressionContext ctx) {
        String name = ctx.ID().getText();
        return new KReference(name);
    }


    @Override
    public Object visitPropertyDotExpression(KinescriptParser.PropertyDotExpressionContext ctx) {
        KExpression propName = new KValue(ctx.ID().getText());
        return new KPropertyAccess(visitExpr(ctx.expr()), propName);
    }

    @Override
    public Object visitParenExpression(KinescriptParser.ParenExpressionContext ctx) {
        // eg. (expr)
        return visitExpr(ctx.expr());
    }

    @Override
    public Object visitRangeExpression(KinescriptParser.RangeExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitInvocationExpression(KinescriptParser.InvocationExpressionContext ctx) {
        String name = ctx.invocation().ID().getText();
        ArrayList<KArg> args = (ArrayList) visitArgs(ctx.invocation().args());
        KExpression exp = new KReference(name,args);
        return exp;
    }

    @Override
    public Object visitPropertyIndexExpression(KinescriptParser.PropertyIndexExpressionContext ctx) {
        return new KPropertyAccess(visitExpr(ctx.expr(0)), visitExpr(ctx.expr(1)));

    }

    @Override
    public Object visitObjectExpression(KinescriptParser.ObjectExpressionContext ctx) {
        HashMap<String, KExpression> object = new HashMap<>();
        for (KinescriptParser.KeyValuePairContext pair : ctx.keyValuePair()) {
            String key = pair.ID().getText();
            KExpression value =  visitExpr(pair.expr());
            object.put(key, value);
        }
        return new KValue(object);
    }

    @Override
    public Object visitKeyValuePair(KinescriptParser.KeyValuePairContext ctx) {
        return null;
    }


    @Override
    public Object visitRange(KinescriptParser.RangeContext ctx) {
        return null;
    }

    @Override
    public Object visitInvocation(KinescriptParser.InvocationContext ctx) {

        String name = ctx.ID().getText();

        ArrayList<KArg> args;
        if (visitArgs(ctx.args()) instanceof List) {
            args = (ArrayList) visitArgs(ctx.args());
        } else {
            throw new RuntimeException("Unknown args type");
        }

        KStatement function = getBuiltInFunction(name, args);
        if (function != null) {
            return function;
        }

        function = (KFunction) program.getScope().get(name);

        if (function == null) {
            throw new RuntimeException("Unknown function: " + name);
        }

        return function;

    }


    @Override
    public Object visitFor(KinescriptParser.ForContext ctx) {
        int start = Integer.parseInt(ctx.INT(0).getText());
        int end = Integer.parseInt(ctx.INT(1).getText());

        List<KStatement> statements = new ArrayList<>();
        for (KinescriptParser.StatementContext statement : ctx.statement()) {
            statements.add((KStatement) visitStatement(statement));
        }

        return new KFor(start, end, statements);

    }

    @Override
    public Object visitArgs(KinescriptParser.ArgsContext ctx) {
        ArrayList args = new ArrayList();
        if (ctx != null && ctx.arg() != null) {
            for (KinescriptParser.ArgContext arg : ctx.arg()) {
                args.add(visitArg(arg));
            }
        }
        return args;
    }

    @Override
    public Object visitArg(KinescriptParser.ArgContext ctx) {

        KExpression expr = (KExpression) visitExpr(ctx.expr());

        return new KArg(expr);

    }

    public KOperator getOperator(String[] possibleOps) {
        for (String op : possibleOps) {

            if (op != null && op.equals("+")) {
                return KOperator.ADD;
            } else if (op != null && op.equals("*")) {
                return KOperator.MUL;
            } else if (op != null && op.equals("-")) {
                return KOperator.SUB;
            } else if (op != null && op.equals("/")) {
                return KOperator.DIV;
            }
        }
        throw new RuntimeException("Unknown operator: " + possibleOps);
    }

    public KOperator getOperator(String op) {
        switch (op) {
            case "+":
                return KOperator.ADD;
            default:
                throw new RuntimeException("Unknown operator: " + op);
        }
    }

    @Override
    public Object visit(ParseTree tree) {
        return null;
    }

    @Override
    public Object visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }

    public static KStatement getFunction(Map<String, Object> scope, String name, ArrayList<KArg> args) {
        if (scope != null && scope.get(name) instanceof KFunction) {
            return (KFunction) scope.get(name);
        }
        KStatement builtIn = getBuiltInFunction(name, args);
        if (builtIn != null) {
            return builtIn;
        }
        return null;
    }

    public static KStatement getBuiltInFunction(String name, ArrayList<KArg> args) {

        //check the built-in functions map
        if (builtInFunctions.containsKey(name)) {
            return builtInFunctions.get(name);
        }

        return null;
    }


    public static void addBuiltInFunction(String name, KStatement function) {
        builtInFunctions.put(name, function);
    }

}
