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
	 * Visit a parse tree produced by {@link KinescriptParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(KinescriptParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(KinescriptParser.NameContext ctx);
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