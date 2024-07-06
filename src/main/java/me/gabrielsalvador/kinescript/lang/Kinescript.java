package me.gabrielsalvador.kinescript.lang;

import me.gabrielsalvador.kinescript.builtins.AddBuiltin;
import me.gabrielsalvador.kinescript.builtins.ClearBuiltin;
import me.gabrielsalvador.kinescript.builtins.KRandom;
import me.gabrielsalvador.kinescript.builtins.MidiBuiltin;
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
            return visitExpr(ctx.expr());
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
        } else if (expr instanceof KinescriptParser.MemberDotExpressionContext) {
            return (KExpression) visitMemberDotExpression((KinescriptParser.MemberDotExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.MemberIndexExpressionContext) {
            return (KExpression) visitMemberIndexExpression((KinescriptParser.MemberIndexExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.InvocationExpressionContext) {
            return (KExpression) visitInvocationExpression((KinescriptParser.InvocationExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.ParenExpressionContext) {
            return (KExpression) visitParenExpression((KinescriptParser.ParenExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.NegateExpressionContext) {
            return (KExpression) visitNegateExpression((KinescriptParser.NegateExpressionContext) expr);
        } else if (expr instanceof KinescriptParser.RangeExpressionContext) {
            return (KExpression) visitRangeExpression((KinescriptParser.RangeExpressionContext) expr);
        } else {
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
        return new KExpression(2, null).setLeft(left).setRight(right).setOperator(operator);
    }


    @Override
    public Object visitIntExpression(KinescriptParser.IntExpressionContext ctx) {
        return new KExpression(0, Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public Object visitStringExpression(KinescriptParser.StringExpressionContext ctx) {
        return new KExpression(0, ctx.STRING().getText());
    }

    @Override
    public Object visitIdExpression(KinescriptParser.IdExpressionContext ctx) {
        String name = ctx.ID().getText();
        return new KExpression(1, name);
    }

    @Override
    public Object visitMemberDotExpression(KinescriptParser.MemberDotExpressionContext ctx) {
        return null;
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
        KExpression exp = new KExpression(1, name);
        ArrayList<KArg> args = (ArrayList) visitArgs(ctx.invocation().args());
        exp.setArgs(args);
        return exp;
    }

    @Override
    public Object visitMemberIndexExpression(KinescriptParser.MemberIndexExpressionContext ctx) {
        return null;
    }


    @Override
    public Object visitMemberDotExpr(KinescriptParser.MemberDotExprContext ctx) {
        return null;
    }

    @Override
    public Object visitMemberIndexExpr(KinescriptParser.MemberIndexExprContext ctx) {
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

        //TODO:replace with a map of built-in functions
        if (name.equals("print")) {
            return new KPrint(args.get(0));
        } else if (name.equals("midi")) {
            return new MidiBuiltin(args);
        } else if (name.equals("random")) {
            return new KRandom();
        } else if (name.equals("clear")) {
            return new ClearBuiltin(args);
        } else if (name.equals("add")) {
            return new AddBuiltin(args);
        }

        KFunction function = (KFunction) program.getScope().get(name);

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

        //if there is an ID, it is the name of the variable
//        if (ctx.ID() != null) {
//            return new KFor(ctx.ID().getText(),start,end,statements);
//        }else{
        return new KFor(start, end, statements);
//        }

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
        } else if (name.equals("print")) {
            return new KPrint();
        } else if (name.equals("midi")) {
            return new KFunction(3, new ArrayList<>());
        } else if (name.equals("random")) {
            return new KRandom();
        } else if (name.equals("add")) {
            return new AddBuiltin(args);
        } else if (name.equals("clear")) {
            return new ClearBuiltin(args);
        }

        return null;
    }


    public static void addBuiltInFunction(String name, KStatement function) {
        builtInFunctions.put(name, function);
    }

}
