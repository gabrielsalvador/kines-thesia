// Generated from ./Kinescript.g4 by ANTLR 4.13.1
package me.gabrielsalvador.kinescript.lang;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class KinescriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		STAR=10, PLUS=11, MINUS=12, DIV=13, OPERATOR=14, ID=15, STRING=16, INT=17, 
		WS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"STAR", "PLUS", "MINUS", "DIV", "OPERATOR", "ID", "STRING", "INT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'('", "')'", "'{'", "'}'", "'for'", "'to'", "','", 
			"'*'", "'+'", "'-'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "STAR", "PLUS", 
			"MINUS", "DIV", "OPERATOR", "ID", "STRING", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public KinescriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Kinescript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0012g\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0004\u000eF\b\u000e"+
		"\u000b\u000e\f\u000eG\u0001\u000f\u0001\u000f\u0005\u000fL\b\u000f\n\u000f"+
		"\f\u000fO\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000fT\b\u000f"+
		"\n\u000f\f\u000fW\t\u000f\u0001\u000f\u0003\u000fZ\b\u000f\u0001\u0010"+
		"\u0004\u0010]\b\u0010\u000b\u0010\f\u0010^\u0001\u0011\u0004\u0011b\b"+
		"\u0011\u000b\u0011\f\u0011c\u0001\u0011\u0001\u0011\u0002MU\u0000\u0012"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012\u0001\u0000\u0004\u0003\u0000*"+
		"+--//\u0002\u0000AZaz\u0001\u000009\u0003\u0000\t\n\r\r  l\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000"+
		"\u0000\u0000#\u0001\u0000\u0000\u0000\u0001%\u0001\u0000\u0000\u0000\u0003"+
		"\'\u0001\u0000\u0000\u0000\u0005)\u0001\u0000\u0000\u0000\u0007+\u0001"+
		"\u0000\u0000\u0000\t-\u0001\u0000\u0000\u0000\u000b/\u0001\u0000\u0000"+
		"\u0000\r1\u0001\u0000\u0000\u0000\u000f5\u0001\u0000\u0000\u0000\u0011"+
		"8\u0001\u0000\u0000\u0000\u0013:\u0001\u0000\u0000\u0000\u0015<\u0001"+
		"\u0000\u0000\u0000\u0017>\u0001\u0000\u0000\u0000\u0019@\u0001\u0000\u0000"+
		"\u0000\u001bB\u0001\u0000\u0000\u0000\u001dE\u0001\u0000\u0000\u0000\u001f"+
		"Y\u0001\u0000\u0000\u0000!\\\u0001\u0000\u0000\u0000#a\u0001\u0000\u0000"+
		"\u0000%&\u0005;\u0000\u0000&\u0002\u0001\u0000\u0000\u0000\'(\u0005=\u0000"+
		"\u0000(\u0004\u0001\u0000\u0000\u0000)*\u0005(\u0000\u0000*\u0006\u0001"+
		"\u0000\u0000\u0000+,\u0005)\u0000\u0000,\b\u0001\u0000\u0000\u0000-.\u0005"+
		"{\u0000\u0000.\n\u0001\u0000\u0000\u0000/0\u0005}\u0000\u00000\f\u0001"+
		"\u0000\u0000\u000012\u0005f\u0000\u000023\u0005o\u0000\u000034\u0005r"+
		"\u0000\u00004\u000e\u0001\u0000\u0000\u000056\u0005t\u0000\u000067\u0005"+
		"o\u0000\u00007\u0010\u0001\u0000\u0000\u000089\u0005,\u0000\u00009\u0012"+
		"\u0001\u0000\u0000\u0000:;\u0005*\u0000\u0000;\u0014\u0001\u0000\u0000"+
		"\u0000<=\u0005+\u0000\u0000=\u0016\u0001\u0000\u0000\u0000>?\u0005-\u0000"+
		"\u0000?\u0018\u0001\u0000\u0000\u0000@A\u0005/\u0000\u0000A\u001a\u0001"+
		"\u0000\u0000\u0000BC\u0007\u0000\u0000\u0000C\u001c\u0001\u0000\u0000"+
		"\u0000DF\u0007\u0001\u0000\u0000ED\u0001\u0000\u0000\u0000FG\u0001\u0000"+
		"\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000H\u001e"+
		"\u0001\u0000\u0000\u0000IM\u0005\"\u0000\u0000JL\t\u0000\u0000\u0000K"+
		"J\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000"+
		"\u0000MK\u0001\u0000\u0000\u0000NP\u0001\u0000\u0000\u0000OM\u0001\u0000"+
		"\u0000\u0000PZ\u0005\"\u0000\u0000QU\u0005\'\u0000\u0000RT\t\u0000\u0000"+
		"\u0000SR\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000UV\u0001\u0000"+
		"\u0000\u0000US\u0001\u0000\u0000\u0000VX\u0001\u0000\u0000\u0000WU\u0001"+
		"\u0000\u0000\u0000XZ\u0005\'\u0000\u0000YI\u0001\u0000\u0000\u0000YQ\u0001"+
		"\u0000\u0000\u0000Z \u0001\u0000\u0000\u0000[]\u0007\u0002\u0000\u0000"+
		"\\[\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_\"\u0001\u0000\u0000\u0000`b\u0007\u0003"+
		"\u0000\u0000a`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000ca\u0001"+
		"\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000"+
		"ef\u0006\u0011\u0000\u0000f$\u0001\u0000\u0000\u0000\u0007\u0000GMUY^"+
		"c\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}