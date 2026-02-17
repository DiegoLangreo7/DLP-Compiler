// Generated from C:/Users/diego/IdeaProjects/DLP-Compiler/src/parser/TSmm.g4 by ANTLR 4.13.2
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TSmmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, ID=5, INT_CONSTANT=6, CHAR_CONSTANT=7, 
		REAL_CONSTANT=8, TRASH=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "ID", "INT_CONSTANT", "CHAR_CONSTANT", 
			"REAL_CONSTANT", "MANTISSA", "EXPONENT", "TRASH", "WHITES", "ONE_LINE_COMMENT", 
			"MULTI_LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "ID", "INT_CONSTANT", "CHAR_CONSTANT", 
			"REAL_CONSTANT", "TRASH"
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


	public TSmmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TSmm.g4"; }

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
		"\u0004\u0000\t\u0087\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0005\u0004(\b\u0004\n\u0004\f\u0004+\t\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u00050\b\u0005\n\u0005\f\u00053\t"+
		"\u0005\u0003\u00055\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006C\b\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007J\b\u0007\u0003"+
		"\u0007L\b\u0007\u0001\b\u0001\b\u0001\b\u0005\bQ\b\b\n\b\f\bT\t\b\u0001"+
		"\b\u0001\b\u0004\bX\b\b\u000b\b\f\bY\u0003\b\\\b\b\u0001\t\u0001\t\u0003"+
		"\t`\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0003\ng\b\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0004\u000bl\b\u000b\u000b\u000b\f\u000bm\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0005\ft\b\f\n\f\f\fw\t\f\u0001\f\u0003\fz\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0005\r\u0080\b\r\n\r\f\r\u0083\t\r\u0001\r"+
		"\u0001\r\u0001\r\u0002u\u0081\u0000\u000e\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\u0000\u0013"+
		"\u0000\u0015\t\u0017\u0000\u0019\u0000\u001b\u0000\u0001\u0000\t\u0003"+
		"\u0000AZ__az\u0004\u000009AZ__az\u0001\u000019\u0001\u000009\u0002\u0000"+
		"nntt\u0002\u0000EEee\u0002\u0000++--\u0003\u0000\t\n\r\r  \u0001\u0001"+
		"\n\n\u0091\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0001\u001d\u0001\u0000\u0000\u0000"+
		"\u0003\u001f\u0001\u0000\u0000\u0000\u0005!\u0001\u0000\u0000\u0000\u0007"+
		"#\u0001\u0000\u0000\u0000\t%\u0001\u0000\u0000\u0000\u000b4\u0001\u0000"+
		"\u0000\u0000\rB\u0001\u0000\u0000\u0000\u000fK\u0001\u0000\u0000\u0000"+
		"\u0011[\u0001\u0000\u0000\u0000\u0013]\u0001\u0000\u0000\u0000\u0015f"+
		"\u0001\u0000\u0000\u0000\u0017k\u0001\u0000\u0000\u0000\u0019o\u0001\u0000"+
		"\u0000\u0000\u001b{\u0001\u0000\u0000\u0000\u001d\u001e\u0005+\u0000\u0000"+
		"\u001e\u0002\u0001\u0000\u0000\u0000\u001f \u0005-\u0000\u0000 \u0004"+
		"\u0001\u0000\u0000\u0000!\"\u0005*\u0000\u0000\"\u0006\u0001\u0000\u0000"+
		"\u0000#$\u0005/\u0000\u0000$\b\u0001\u0000\u0000\u0000%)\u0007\u0000\u0000"+
		"\u0000&(\u0007\u0001\u0000\u0000\'&\u0001\u0000\u0000\u0000(+\u0001\u0000"+
		"\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*\n\u0001"+
		"\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000,5\u00050\u0000\u0000-1\u0007"+
		"\u0002\u0000\u0000.0\u0007\u0003\u0000\u0000/.\u0001\u0000\u0000\u0000"+
		"03\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u000012\u0001\u0000\u0000"+
		"\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u00004,\u0001\u0000"+
		"\u0000\u00004-\u0001\u0000\u0000\u00005\f\u0001\u0000\u0000\u000067\u0005"+
		"\'\u0000\u000078\t\u0000\u0000\u00008C\u0005\'\u0000\u00009:\u0005\'\u0000"+
		"\u0000:;\u0005\\\u0000\u0000;<\u0007\u0004\u0000\u0000<C\u0005\'\u0000"+
		"\u0000=>\u0005\'\u0000\u0000>?\u0005\\\u0000\u0000?@\u0003\u000b\u0005"+
		"\u0000@A\u0005\'\u0000\u0000AC\u0001\u0000\u0000\u0000B6\u0001\u0000\u0000"+
		"\u0000B9\u0001\u0000\u0000\u0000B=\u0001\u0000\u0000\u0000C\u000e\u0001"+
		"\u0000\u0000\u0000DE\u0003\u000b\u0005\u0000EF\u0003\u0013\t\u0000FL\u0001"+
		"\u0000\u0000\u0000GI\u0003\u0011\b\u0000HJ\u0003\u0013\t\u0000IH\u0001"+
		"\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JL\u0001\u0000\u0000\u0000"+
		"KD\u0001\u0000\u0000\u0000KG\u0001\u0000\u0000\u0000L\u0010\u0001\u0000"+
		"\u0000\u0000MN\u0003\u000b\u0005\u0000NR\u0005.\u0000\u0000OQ\u0007\u0003"+
		"\u0000\u0000PO\u0001\u0000\u0000\u0000QT\u0001\u0000\u0000\u0000RP\u0001"+
		"\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000S\\\u0001\u0000\u0000\u0000"+
		"TR\u0001\u0000\u0000\u0000UW\u0005.\u0000\u0000VX\u0007\u0003\u0000\u0000"+
		"WV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000"+
		"\u0000YZ\u0001\u0000\u0000\u0000Z\\\u0001\u0000\u0000\u0000[M\u0001\u0000"+
		"\u0000\u0000[U\u0001\u0000\u0000\u0000\\\u0012\u0001\u0000\u0000\u0000"+
		"]_\u0007\u0005\u0000\u0000^`\u0007\u0006\u0000\u0000_^\u0001\u0000\u0000"+
		"\u0000_`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0003\u000b"+
		"\u0005\u0000b\u0014\u0001\u0000\u0000\u0000cg\u0003\u0017\u000b\u0000"+
		"dg\u0003\u0019\f\u0000eg\u0003\u001b\r\u0000fc\u0001\u0000\u0000\u0000"+
		"fd\u0001\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hi\u0006\n\u0000\u0000i\u0016\u0001\u0000\u0000\u0000jl\u0007\u0007"+
		"\u0000\u0000kj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mk\u0001"+
		"\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000n\u0018\u0001\u0000\u0000"+
		"\u0000op\u0005/\u0000\u0000pq\u0005/\u0000\u0000qu\u0001\u0000\u0000\u0000"+
		"rt\t\u0000\u0000\u0000sr\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000"+
		"uv\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000"+
		"\u0000wu\u0001\u0000\u0000\u0000xz\u0007\b\u0000\u0000yx\u0001\u0000\u0000"+
		"\u0000z\u001a\u0001\u0000\u0000\u0000{|\u0005/\u0000\u0000|}\u0005*\u0000"+
		"\u0000}\u0081\u0001\u0000\u0000\u0000~\u0080\t\u0000\u0000\u0000\u007f"+
		"~\u0001\u0000\u0000\u0000\u0080\u0083\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0082\u0084"+
		"\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0005*\u0000\u0000\u0085\u0086\u0005/\u0000\u0000\u0086\u001c\u0001\u0000"+
		"\u0000\u0000\u0010\u0000)14BIKRY[_fmuy\u0081\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}