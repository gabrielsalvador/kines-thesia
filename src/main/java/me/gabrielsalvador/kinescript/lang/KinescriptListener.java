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
	 * Enter a parse tree produced by the {@code NegateExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegateExpression(KinescriptParser.NegateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegateExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegateExpression(KinescriptParser.NegateExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OperationExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOperationExpression(KinescriptParser.OperationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OperationExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOperationExpression(KinescriptParser.OperationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpression(KinescriptParser.IntExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpression(KinescriptParser.IntExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(KinescriptParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(KinescriptParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpression(KinescriptParser.IdExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpression(KinescriptParser.IdExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDotExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMemberDotExpression(KinescriptParser.MemberDotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDotExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMemberDotExpression(KinescriptParser.MemberDotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(KinescriptParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(KinescriptParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RangeExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRangeExpression(KinescriptParser.RangeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RangeExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRangeExpression(KinescriptParser.RangeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InvocationExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInvocationExpression(KinescriptParser.InvocationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InvocationExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInvocationExpression(KinescriptParser.InvocationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberIndexExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMemberIndexExpression(KinescriptParser.MemberIndexExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberIndexExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMemberIndexExpression(KinescriptParser.MemberIndexExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#memberDotExpr}.
	 * @param ctx the parse tree
	 */
	void enterMemberDotExpr(KinescriptParser.MemberDotExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#memberDotExpr}.
	 * @param ctx the parse tree
	 */
	void exitMemberDotExpr(KinescriptParser.MemberDotExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#memberIndexExpr}.
	 * @param ctx the parse tree
	 */
	void enterMemberIndexExpr(KinescriptParser.MemberIndexExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#memberIndexExpr}.
	 * @param ctx the parse tree
	 */
	void exitMemberIndexExpr(KinescriptParser.MemberIndexExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KinescriptParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(KinescriptParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KinescriptParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(KinescriptParser.RangeContext ctx);
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