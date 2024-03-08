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
	 * Enter a parse tree produced by {@link KinescriptParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(KinescriptParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(KinescriptParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(KinescriptParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(KinescriptParser.NameContext ctx);
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