// Generated from ./Kinescript.g4 by ANTLR 4.13.1
package me.gabrielsalvador.kinescript.lang;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KinescriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KinescriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(KinescriptParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(KinescriptParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(KinescriptParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(KinescriptParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(KinescriptParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#invocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvocation(KinescriptParser.InvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(KinescriptParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(KinescriptParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(KinescriptParser.ArgContext ctx);
}