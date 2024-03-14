package me.gabrielsalvador.kinescript.lang;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Kinescript implements KinescriptVisitor{

    @Override
    public Object visitProgram(KinescriptParser.ProgramContext ctx) {
        return null;
    }

    @Override
    public Object visitCommand(KinescriptParser.CommandContext ctx) {
        return null;
    }

    @Override
    public Object visitName(KinescriptParser.NameContext ctx) {
        return null;
    }

    @Override
    public Object visitArgs(KinescriptParser.ArgsContext ctx) {
        return null;
    }

    @Override
    public Object visitArg(KinescriptParser.ArgContext ctx) {
        return null;
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
