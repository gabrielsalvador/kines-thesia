// Generated from ./Kgrammar.g4 by ANTLR 4.13.1
package me.gabrielsalvador.kinescript;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KgrammarParser}.
 */
public interface KgrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KgrammarParser#commands}.
	 * @param ctx the parse tree
	 */
	void enterCommands(KgrammarParser.CommandsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KgrammarParser#commands}.
	 * @param ctx the parse tree
	 */
	void exitCommands(KgrammarParser.CommandsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KgrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(KgrammarParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link KgrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(KgrammarParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link KgrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(KgrammarParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link KgrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(KgrammarParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link KgrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(KgrammarParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KgrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(KgrammarParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KgrammarParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(KgrammarParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link KgrammarParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(KgrammarParser.ArgContext ctx);
}