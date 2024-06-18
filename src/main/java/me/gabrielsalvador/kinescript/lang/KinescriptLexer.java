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
		T__9=10, NOTE=11, STAR=12, PLUS=13, MINUS=14, DIV=15, OPERATOR=16, ID=17, 
		STRING=18, INT=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "NOTE", "STAR", "PLUS", "MINUS", "DIV", "OPERATOR", "ID", "STRING", 
			"INT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'('", "')'", "'{'", "'}'", "'to'", "'for'", "'as'", 
			"','", null, "'*'", "'+'", "'-'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "NOTE", 
			"STAR", "PLUS", "MINUS", "DIV", "OPERATOR", "ID", "STRING", "INT", "WS"
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
		"\u0004\u0000\u0014u\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0003\nD\b\n\u0001\n\u0003\n"+
		"G\b\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0004\u0010T\b\u0010"+
		"\u000b\u0010\f\u0010U\u0001\u0011\u0001\u0011\u0005\u0011Z\b\u0011\n\u0011"+
		"\f\u0011]\t\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011b\b\u0011"+
		"\n\u0011\f\u0011e\t\u0011\u0001\u0011\u0003\u0011h\b\u0011\u0001\u0012"+
		"\u0004\u0012k\b\u0012\u000b\u0012\f\u0012l\u0001\u0013\u0004\u0013p\b"+
		"\u0013\u000b\u0013\f\u0013q\u0001\u0013\u0001\u0013\u0002[c\u0000\u0014"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014\u0001\u0000\u0006"+
		"\u0001\u0000AG\u0002\u0000##bb\u0001\u000009\u0003\u0000*+--//\u0002\u0000"+
		"AZaz\u0003\u0000\t\n\r\r  |\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0001)\u0001\u0000\u0000\u0000\u0003+\u0001\u0000\u0000\u0000\u0005-"+
		"\u0001\u0000\u0000\u0000\u0007/\u0001\u0000\u0000\u0000\t1\u0001\u0000"+
		"\u0000\u0000\u000b3\u0001\u0000\u0000\u0000\r5\u0001\u0000\u0000\u0000"+
		"\u000f8\u0001\u0000\u0000\u0000\u0011<\u0001\u0000\u0000\u0000\u0013?"+
		"\u0001\u0000\u0000\u0000\u0015A\u0001\u0000\u0000\u0000\u0017H\u0001\u0000"+
		"\u0000\u0000\u0019J\u0001\u0000\u0000\u0000\u001bL\u0001\u0000\u0000\u0000"+
		"\u001dN\u0001\u0000\u0000\u0000\u001fP\u0001\u0000\u0000\u0000!S\u0001"+
		"\u0000\u0000\u0000#g\u0001\u0000\u0000\u0000%j\u0001\u0000\u0000\u0000"+
		"\'o\u0001\u0000\u0000\u0000)*\u0005;\u0000\u0000*\u0002\u0001\u0000\u0000"+
		"\u0000+,\u0005=\u0000\u0000,\u0004\u0001\u0000\u0000\u0000-.\u0005(\u0000"+
		"\u0000.\u0006\u0001\u0000\u0000\u0000/0\u0005)\u0000\u00000\b\u0001\u0000"+
		"\u0000\u000012\u0005{\u0000\u00002\n\u0001\u0000\u0000\u000034\u0005}"+
		"\u0000\u00004\f\u0001\u0000\u0000\u000056\u0005t\u0000\u000067\u0005o"+
		"\u0000\u00007\u000e\u0001\u0000\u0000\u000089\u0005f\u0000\u00009:\u0005"+
		"o\u0000\u0000:;\u0005r\u0000\u0000;\u0010\u0001\u0000\u0000\u0000<=\u0005"+
		"a\u0000\u0000=>\u0005s\u0000\u0000>\u0012\u0001\u0000\u0000\u0000?@\u0005"+
		",\u0000\u0000@\u0014\u0001\u0000\u0000\u0000AC\u0007\u0000\u0000\u0000"+
		"BD\u0007\u0001\u0000\u0000CB\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000"+
		"\u0000DF\u0001\u0000\u0000\u0000EG\u0007\u0002\u0000\u0000FE\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000G\u0016\u0001\u0000\u0000\u0000"+
		"HI\u0005*\u0000\u0000I\u0018\u0001\u0000\u0000\u0000JK\u0005+\u0000\u0000"+
		"K\u001a\u0001\u0000\u0000\u0000LM\u0005-\u0000\u0000M\u001c\u0001\u0000"+
		"\u0000\u0000NO\u0005/\u0000\u0000O\u001e\u0001\u0000\u0000\u0000PQ\u0007"+
		"\u0003\u0000\u0000Q \u0001\u0000\u0000\u0000RT\u0007\u0004\u0000\u0000"+
		"SR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000US\u0001\u0000\u0000"+
		"\u0000UV\u0001\u0000\u0000\u0000V\"\u0001\u0000\u0000\u0000W[\u0005\""+
		"\u0000\u0000XZ\t\u0000\u0000\u0000YX\u0001\u0000\u0000\u0000Z]\u0001\u0000"+
		"\u0000\u0000[\\\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000\\^\u0001"+
		"\u0000\u0000\u0000][\u0001\u0000\u0000\u0000^h\u0005\"\u0000\u0000_c\u0005"+
		"\'\u0000\u0000`b\t\u0000\u0000\u0000a`\u0001\u0000\u0000\u0000be\u0001"+
		"\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000"+
		"df\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000fh\u0005\'\u0000\u0000"+
		"gW\u0001\u0000\u0000\u0000g_\u0001\u0000\u0000\u0000h$\u0001\u0000\u0000"+
		"\u0000ik\u0007\u0002\u0000\u0000ji\u0001\u0000\u0000\u0000kl\u0001\u0000"+
		"\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000m&\u0001"+
		"\u0000\u0000\u0000np\u0007\u0005\u0000\u0000on\u0001\u0000\u0000\u0000"+
		"pq\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000"+
		"\u0000rs\u0001\u0000\u0000\u0000st\u0006\u0013\u0000\u0000t(\u0001\u0000"+
		"\u0000\u0000\t\u0000CFU[cglq\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}