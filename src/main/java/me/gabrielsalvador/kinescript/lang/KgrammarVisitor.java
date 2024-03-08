// Generated from ./Kgrammar.g4 by ANTLR 4.13.1
package me.gabrielsalvador.kinescript.lang;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KgrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KgrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KgrammarParser#commands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommands(KgrammarParser.CommandsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KgrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(KgrammarParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link KgrammarParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(KgrammarParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link KgrammarParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(KgrammarParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KgrammarParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(KgrammarParser.ArgContext ctx);
}