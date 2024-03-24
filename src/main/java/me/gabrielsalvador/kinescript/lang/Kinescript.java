package me.gabrielsalvador.kinescript.lang;

import me.gabrielsalvador.kinescript.MidiBuiltin;
import me.gabrielsalvador.kinescript.ast.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class Kinescript implements KinescriptVisitor{

    private KFunction program;

    public static KFunction compileFunction(String code) {
        try {
            KinescriptLexer lexer = new KinescriptLexer(new org.antlr.v4.runtime.ANTLRInputStream(code));
            KinescriptParser parser = new KinescriptParser(new org.antlr.v4.runtime.CommonTokenStream(lexer));
            Kinescript kinescript = new Kinescript();
            KFunction func =  (KFunction) kinescript.visitProgram(parser.program());
            func.setSourceCode(code);
            return func;
        }catch (Exception e){
            throw new RuntimeException("Error compiling function: " + e.getMessage());
        }
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
            return ctx.STRING().getText();
        }else if (ctx.INT() != null) {
            return Integer.parseInt(ctx.INT().getText());
        }
        else if (ctx.ID() != null) {
            String name = ctx.ID().getText();
            return program.getScope().get(name);
        } else if (ctx.invocation() != null) {
            return visitInvocation(ctx.invocation());
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
        }

        KFunction function = (KFunction) program.getScope().get(name);
        if (function == null) {
            throw new RuntimeException("Unknown function: " + name);
        }



        if (args.size() != function.getParameterNumber() ){
            throw new RuntimeException("Wrong number of arguments for function: " + name);
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

        return new KFor(start,end,statements);
    }

    @Override
    public Object visitArgs(KinescriptParser.ArgsContext ctx) {
        ArrayList args = new ArrayList();
        for (KinescriptParser.ArgContext arg : ctx.arg()) {
            args.add(visitArg(arg));
        }
        return args;
    }

    @Override
    public Object visitArg(KinescriptParser.ArgContext ctx) {
        if (ctx.STRING() != null) {
            String text = ctx.STRING().getText();
            return new KArg(false, text);

        } if (ctx.INT() != null) {
            int value = Integer.parseInt(ctx.INT().getText());
            return new KArg(false, value);
        }
        else if (ctx.ID() != null) {
            String name = ctx.ID().getText();
            return new KArg(true, name);

        }
        else{
            throw new RuntimeException("Unknown arg type");
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
}
