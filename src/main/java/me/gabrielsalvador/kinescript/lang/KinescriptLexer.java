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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, NOTE=15, STAR=16, PLUS=17, 
		MINUS=18, DIV=19, OPERATOR=20, ID=21, STRING=22, INT=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "NOTE", "STAR", "PLUS", "MINUS", 
			"DIV", "OPERATOR", "ID", "STRING", "INT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'function'", "'('", "')'", "'{'", "'}'", "'.'", 
			"'['", "']'", "'to'", "'for'", "'as'", "','", null, "'*'", "'+'", "'-'", 
			"'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "NOTE", "STAR", "PLUS", "MINUS", "DIV", "OPERATOR", 
			"ID", "STRING", "INT", "WS"
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
		"\u0004\u0000\u0018\u008c\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0003\u000e[\b\u000e\u0001\u000e\u0003\u000e^\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0004\u0014k\b"+
		"\u0014\u000b\u0014\f\u0014l\u0001\u0015\u0001\u0015\u0005\u0015q\b\u0015"+
		"\n\u0015\f\u0015t\t\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015"+
		"y\b\u0015\n\u0015\f\u0015|\t\u0015\u0001\u0015\u0003\u0015\u007f\b\u0015"+
		"\u0001\u0016\u0004\u0016\u0082\b\u0016\u000b\u0016\f\u0016\u0083\u0001"+
		"\u0017\u0004\u0017\u0087\b\u0017\u000b\u0017\f\u0017\u0088\u0001\u0017"+
		"\u0001\u0017\u0002rz\u0000\u0018\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015+\u0016-\u0017/\u0018\u0001\u0000\u0006\u0001\u0000AG\u0002"+
		"\u0000##bb\u0001\u000009\u0003\u0000*+--//\u0002\u0000AZaz\u0003\u0000"+
		"\t\n\r\r  \u0093\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000"+
		"%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00011\u0001\u0000\u0000\u0000\u0003"+
		"3\u0001\u0000\u0000\u0000\u00055\u0001\u0000\u0000\u0000\u0007>\u0001"+
		"\u0000\u0000\u0000\t@\u0001\u0000\u0000\u0000\u000bB\u0001\u0000\u0000"+
		"\u0000\rD\u0001\u0000\u0000\u0000\u000fF\u0001\u0000\u0000\u0000\u0011"+
		"H\u0001\u0000\u0000\u0000\u0013J\u0001\u0000\u0000\u0000\u0015L\u0001"+
		"\u0000\u0000\u0000\u0017O\u0001\u0000\u0000\u0000\u0019S\u0001\u0000\u0000"+
		"\u0000\u001bV\u0001\u0000\u0000\u0000\u001dX\u0001\u0000\u0000\u0000\u001f"+
		"_\u0001\u0000\u0000\u0000!a\u0001\u0000\u0000\u0000#c\u0001\u0000\u0000"+
		"\u0000%e\u0001\u0000\u0000\u0000\'g\u0001\u0000\u0000\u0000)j\u0001\u0000"+
		"\u0000\u0000+~\u0001\u0000\u0000\u0000-\u0081\u0001\u0000\u0000\u0000"+
		"/\u0086\u0001\u0000\u0000\u000012\u0005;\u0000\u00002\u0002\u0001\u0000"+
		"\u0000\u000034\u0005=\u0000\u00004\u0004\u0001\u0000\u0000\u000056\u0005"+
		"f\u0000\u000067\u0005u\u0000\u000078\u0005n\u0000\u000089\u0005c\u0000"+
		"\u00009:\u0005t\u0000\u0000:;\u0005i\u0000\u0000;<\u0005o\u0000\u0000"+
		"<=\u0005n\u0000\u0000=\u0006\u0001\u0000\u0000\u0000>?\u0005(\u0000\u0000"+
		"?\b\u0001\u0000\u0000\u0000@A\u0005)\u0000\u0000A\n\u0001\u0000\u0000"+
		"\u0000BC\u0005{\u0000\u0000C\f\u0001\u0000\u0000\u0000DE\u0005}\u0000"+
		"\u0000E\u000e\u0001\u0000\u0000\u0000FG\u0005.\u0000\u0000G\u0010\u0001"+
		"\u0000\u0000\u0000HI\u0005[\u0000\u0000I\u0012\u0001\u0000\u0000\u0000"+
		"JK\u0005]\u0000\u0000K\u0014\u0001\u0000\u0000\u0000LM\u0005t\u0000\u0000"+
		"MN\u0005o\u0000\u0000N\u0016\u0001\u0000\u0000\u0000OP\u0005f\u0000\u0000"+
		"PQ\u0005o\u0000\u0000QR\u0005r\u0000\u0000R\u0018\u0001\u0000\u0000\u0000"+
		"ST\u0005a\u0000\u0000TU\u0005s\u0000\u0000U\u001a\u0001\u0000\u0000\u0000"+
		"VW\u0005,\u0000\u0000W\u001c\u0001\u0000\u0000\u0000XZ\u0007\u0000\u0000"+
		"\u0000Y[\u0007\u0001\u0000\u0000ZY\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[]\u0001\u0000\u0000\u0000\\^\u0007\u0002\u0000\u0000]\\\u0001"+
		"\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\u001e\u0001\u0000\u0000"+
		"\u0000_`\u0005*\u0000\u0000` \u0001\u0000\u0000\u0000ab\u0005+\u0000\u0000"+
		"b\"\u0001\u0000\u0000\u0000cd\u0005-\u0000\u0000d$\u0001\u0000\u0000\u0000"+
		"ef\u0005/\u0000\u0000f&\u0001\u0000\u0000\u0000gh\u0007\u0003\u0000\u0000"+
		"h(\u0001\u0000\u0000\u0000ik\u0007\u0004\u0000\u0000ji\u0001\u0000\u0000"+
		"\u0000kl\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000"+
		"\u0000\u0000m*\u0001\u0000\u0000\u0000nr\u0005\"\u0000\u0000oq\t\u0000"+
		"\u0000\u0000po\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000\u0000rs\u0001"+
		"\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000su\u0001\u0000\u0000\u0000"+
		"tr\u0001\u0000\u0000\u0000u\u007f\u0005\"\u0000\u0000vz\u0005\'\u0000"+
		"\u0000wy\t\u0000\u0000\u0000xw\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000"+
		"\u0000z{\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000{}\u0001\u0000"+
		"\u0000\u0000|z\u0001\u0000\u0000\u0000}\u007f\u0005\'\u0000\u0000~n\u0001"+
		"\u0000\u0000\u0000~v\u0001\u0000\u0000\u0000\u007f,\u0001\u0000\u0000"+
		"\u0000\u0080\u0082\u0007\u0002\u0000\u0000\u0081\u0080\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084.\u0001\u0000\u0000\u0000"+
		"\u0085\u0087\u0007\u0005\u0000\u0000\u0086\u0085\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000"+
		"\u008a\u008b\u0006\u0017\u0000\u0000\u008b0\u0001\u0000\u0000\u0000\t"+
		"\u0000Z]lrz~\u0083\u0088\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}