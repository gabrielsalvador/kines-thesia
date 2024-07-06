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
	 * Visit a parse tree produced by the {@code NegateExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpression(KinescriptParser.NegateExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OperationExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationExpression(KinescriptParser.OperationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpression(KinescriptParser.IntExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpression(KinescriptParser.StringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpression(KinescriptParser.IdExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberDotExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDotExpression(KinescriptParser.MemberDotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(KinescriptParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RangeExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpression(KinescriptParser.RangeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InvocationExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvocationExpression(KinescriptParser.InvocationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberIndexExpression}
	 * labeled alternative in {@link KinescriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberIndexExpression(KinescriptParser.MemberIndexExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#memberDotExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDotExpr(KinescriptParser.MemberDotExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#memberIndexExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberIndexExpr(KinescriptParser.MemberIndexExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KinescriptParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(KinescriptParser.RangeContext ctx);
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