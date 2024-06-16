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
import java.util.List;
import java.util.Map;

public class Kinescript implements KinescriptVisitor{

    private KFunction program;

    public static KFunction compileFunction(String code) {

            KinescriptLexer lexer = new KinescriptLexer(new org.antlr.v4.runtime.ANTLRInputStream(code));
            KinescriptParser parser = new KinescriptParser(new org.antlr.v4.runtime.CommonTokenStream(lexer));
            Kinescript kinescript = new Kinescript();
            KFunction func =  (KFunction) kinescript.visitProgram(parser.program());
            func.setSourceCode(code);
            return func;

    }

    @Override
    public Object visitProgram(KinescriptParser.ProgramContext ctx) {

        ArrayList statements = new ArrayList();
        program = new KFunction(0,statements);

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
        } else if (ctx.for_() != null) {
            return visitFor(ctx.for_());
        }
        else{
            throw new RuntimeException("Unknown statement type");
        }

    }

    @Override
    public Object visitAssignment(KinescriptParser.AssignmentContext ctx) {
        String name = ctx.ID().getText();
        Object value = visitExpr(ctx.expr());


        //the variable is added to the scope at compile because it will be used deeper in the AST for compilation
        program.getScope().put(name, value);

        return new KAssign(name, value);

    }

    @Override
    public Object visitDefinition(KinescriptParser.DefinitionContext ctx) {
        int parameterNumber = ctx.args().arg().size();

        List<KStatement> statements = new ArrayList<>();
        for (KinescriptParser.StatementContext statement : ctx.statement()) {
            statements.add((KStatement) visitStatement(statement));
        }

        return new KFunction(parameterNumber,statements);
    }


    @Override
    public Object visitExpr(KinescriptParser.ExprContext ctx) {
        if (ctx.STRING() != null) {
            //get text without quotes
            String text = ctx.STRING().getText();
            return new KExpression(false, text.substring(1, text.length() - 1));
        }else if (ctx.INT() != null) {
            return new KExpression(false, Integer.parseInt(ctx.INT().getText()));
        }
        else if (ctx.ID() != null) {
            String name = ctx.ID().getText();
            return new KExpression(true, name);
        } else if (ctx.invocation() != null) {

            String name = ctx.invocation().ID().getText();
            KExpression exp =  new KExpression(true, name);
            ArrayList<KArg> args =  (ArrayList) visitArgs(ctx.invocation().args());
            exp.setArgs(args);
            return exp;
        } else if (ctx.expr() != null) {
            return visitExpr(ctx.expr());
        }else {
            throw new RuntimeException("Unknown expression type");
        }

    }

    @Override
    public Object visitInvocation(KinescriptParser.InvocationContext ctx) {

        String name = ctx.ID().getText();

        ArrayList<KArg> args;
        if (visitArgs(ctx.args()) instanceof List) {
            args = (ArrayList) visitArgs(ctx.args() );
        }else{
            throw new RuntimeException("Unknown args type");
        }

        //TODO:replace with a map of built-in functions
        if (name.equals("print")) {
            return new KPrint( args.get(0));
        }else if (name.equals("midi")) {
            return new MidiBuiltin( args );
        }else if (name.equals("random")) {
            return new KRandom( );
        }else if (name.equals("clear")) {
            return new ClearBuiltin( args );
        }else if (name.equals("add")) {
            return new AddBuiltin( args );
        }

        KFunction function = (KFunction) program.getScope().get(name);
        if (function == null) {
            throw new RuntimeException("Unknown function: " + name);
        }





        return function.execute(program.getScope());

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
        if (ctx.ID() != null) {
            return new KFor(ctx.ID().getText(),start,end,statements);
        }else{
            return new KFor(start,end,statements);
        }

    }

    @Override
    public Object visitArgs(KinescriptParser.ArgsContext ctx) {
        ArrayList args = new ArrayList();
        if(ctx != null && ctx.arg() != null){
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
        if (scope.get(name) instanceof KFunction) {
            return (KFunction) scope.get(name);
        }
        KStatement builtIn = getBuiltInFunction(name,args);
        if (builtIn != null) {
            return builtIn;
        }
        return null;
    }

    public static KStatement getBuiltInFunction(String name, ArrayList<KArg> args) {
        if (name.equals("print")) {
            return new KPrint();
        }else if (name.equals("midi")) {
            return new KFunction(3, new ArrayList<>());
        }else if (name.equals("random")) {
            return new KRandom();
        }else if (name.equals("add")) {
            return new AddBuiltin( args );
        }
        return null;
    }
}
