// Generated from ./Kinescript.g4 by ANTLR 4.13.1
package me.gabrielsalvador.kinescript.lang;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KinescriptParser}.
 */
public interface KinescriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(KinescriptParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(KinescriptParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(KinescriptParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(KinescriptParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(KinescriptParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(KinescriptParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(KinescriptParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(KinescriptParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(KinescriptParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(KinescriptParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#invocation}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(KinescriptParser.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#invocation}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(KinescriptParser.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#for}.
	 * @param ctx the parse tree
	 */
	void enterFor(KinescriptParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#for}.
	 * @param ctx the parse tree
	 */
	void exitFor(KinescriptParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(KinescriptParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(KinescriptParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(KinescriptParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(KinescriptParser.ArgContext ctx);
}