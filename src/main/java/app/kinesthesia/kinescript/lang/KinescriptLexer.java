// Generated from ./Kinescript.g4 by ANTLR 4.13.1
package app.kinesthesia.kinescript.lang;
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, NOTE=16, STAR=17, 
		PLUS=18, MINUS=19, DIV=20, OPERATOR=21, ID=22, STRING=23, INT=24, WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "NOTE", "STAR", 
			"PLUS", "MINUS", "DIV", "OPERATOR", "ID", "STRING", "INT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'function'", "'('", "')'", "'{'", "'}'", "'.'", 
			"'['", "']'", "','", "':'", "'to'", "'for'", "'as'", null, "'*'", "'+'", 
			"'-'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "NOTE", "STAR", "PLUS", "MINUS", "DIV", "OPERATOR", 
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
		"\u0004\u0000\u0019\u0090\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f_\b"+
		"\u000f\u0001\u000f\u0003\u000fb\b\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0004\u0015o\b\u0015\u000b\u0015\f\u0015"+
		"p\u0001\u0016\u0001\u0016\u0005\u0016u\b\u0016\n\u0016\f\u0016x\t\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016}\b\u0016\n\u0016\f\u0016"+
		"\u0080\t\u0016\u0001\u0016\u0003\u0016\u0083\b\u0016\u0001\u0017\u0004"+
		"\u0017\u0086\b\u0017\u000b\u0017\f\u0017\u0087\u0001\u0018\u0004\u0018"+
		"\u008b\b\u0018\u000b\u0018\f\u0018\u008c\u0001\u0018\u0001\u0018\u0002"+
		"v~\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u0019\u0001\u0000\u0006\u0001\u0000AG\u0002\u0000"+
		"##bb\u0001\u000009\u0003\u0000*+--//\u0002\u0000AZaz\u0003\u0000\t\n\r"+
		"\r  \u0097\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0001"+
		"3\u0001\u0000\u0000\u0000\u00035\u0001\u0000\u0000\u0000\u00057\u0001"+
		"\u0000\u0000\u0000\u0007@\u0001\u0000\u0000\u0000\tB\u0001\u0000\u0000"+
		"\u0000\u000bD\u0001\u0000\u0000\u0000\rF\u0001\u0000\u0000\u0000\u000f"+
		"H\u0001\u0000\u0000\u0000\u0011J\u0001\u0000\u0000\u0000\u0013L\u0001"+
		"\u0000\u0000\u0000\u0015N\u0001\u0000\u0000\u0000\u0017P\u0001\u0000\u0000"+
		"\u0000\u0019R\u0001\u0000\u0000\u0000\u001bU\u0001\u0000\u0000\u0000\u001d"+
		"Y\u0001\u0000\u0000\u0000\u001f\\\u0001\u0000\u0000\u0000!c\u0001\u0000"+
		"\u0000\u0000#e\u0001\u0000\u0000\u0000%g\u0001\u0000\u0000\u0000\'i\u0001"+
		"\u0000\u0000\u0000)k\u0001\u0000\u0000\u0000+n\u0001\u0000\u0000\u0000"+
		"-\u0082\u0001\u0000\u0000\u0000/\u0085\u0001\u0000\u0000\u00001\u008a"+
		"\u0001\u0000\u0000\u000034\u0005;\u0000\u00004\u0002\u0001\u0000\u0000"+
		"\u000056\u0005=\u0000\u00006\u0004\u0001\u0000\u0000\u000078\u0005f\u0000"+
		"\u000089\u0005u\u0000\u00009:\u0005n\u0000\u0000:;\u0005c\u0000\u0000"+
		";<\u0005t\u0000\u0000<=\u0005i\u0000\u0000=>\u0005o\u0000\u0000>?\u0005"+
		"n\u0000\u0000?\u0006\u0001\u0000\u0000\u0000@A\u0005(\u0000\u0000A\b\u0001"+
		"\u0000\u0000\u0000BC\u0005)\u0000\u0000C\n\u0001\u0000\u0000\u0000DE\u0005"+
		"{\u0000\u0000E\f\u0001\u0000\u0000\u0000FG\u0005}\u0000\u0000G\u000e\u0001"+
		"\u0000\u0000\u0000HI\u0005.\u0000\u0000I\u0010\u0001\u0000\u0000\u0000"+
		"JK\u0005[\u0000\u0000K\u0012\u0001\u0000\u0000\u0000LM\u0005]\u0000\u0000"+
		"M\u0014\u0001\u0000\u0000\u0000NO\u0005,\u0000\u0000O\u0016\u0001\u0000"+
		"\u0000\u0000PQ\u0005:\u0000\u0000Q\u0018\u0001\u0000\u0000\u0000RS\u0005"+
		"t\u0000\u0000ST\u0005o\u0000\u0000T\u001a\u0001\u0000\u0000\u0000UV\u0005"+
		"f\u0000\u0000VW\u0005o\u0000\u0000WX\u0005r\u0000\u0000X\u001c\u0001\u0000"+
		"\u0000\u0000YZ\u0005a\u0000\u0000Z[\u0005s\u0000\u0000[\u001e\u0001\u0000"+
		"\u0000\u0000\\^\u0007\u0000\u0000\u0000]_\u0007\u0001\u0000\u0000^]\u0001"+
		"\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_a\u0001\u0000\u0000\u0000"+
		"`b\u0007\u0002\u0000\u0000a`\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000"+
		"\u0000b \u0001\u0000\u0000\u0000cd\u0005*\u0000\u0000d\"\u0001\u0000\u0000"+
		"\u0000ef\u0005+\u0000\u0000f$\u0001\u0000\u0000\u0000gh\u0005-\u0000\u0000"+
		"h&\u0001\u0000\u0000\u0000ij\u0005/\u0000\u0000j(\u0001\u0000\u0000\u0000"+
		"kl\u0007\u0003\u0000\u0000l*\u0001\u0000\u0000\u0000mo\u0007\u0004\u0000"+
		"\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000pq\u0001\u0000\u0000\u0000q,\u0001\u0000\u0000\u0000rv\u0005"+
		"\"\u0000\u0000su\t\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000ux\u0001"+
		"\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000"+
		"wy\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000y\u0083\u0005\"\u0000"+
		"\u0000z~\u0005\'\u0000\u0000{}\t\u0000\u0000\u0000|{\u0001\u0000\u0000"+
		"\u0000}\u0080\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000~"+
		"|\u0001\u0000\u0000\u0000\u007f\u0081\u0001\u0000\u0000\u0000\u0080~\u0001"+
		"\u0000\u0000\u0000\u0081\u0083\u0005\'\u0000\u0000\u0082r\u0001\u0000"+
		"\u0000\u0000\u0082z\u0001\u0000\u0000\u0000\u0083.\u0001\u0000\u0000\u0000"+
		"\u0084\u0086\u0007\u0002\u0000\u0000\u0085\u0084\u0001\u0000\u0000\u0000"+
		"\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0001\u0000\u0000\u0000\u00880\u0001\u0000\u0000\u0000\u0089"+
		"\u008b\u0007\u0005\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b"+
		"\u008c\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e"+
		"\u008f\u0006\u0018\u0000\u0000\u008f2\u0001\u0000\u0000\u0000\t\u0000"+
		"^apv~\u0082\u0087\u008c\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}