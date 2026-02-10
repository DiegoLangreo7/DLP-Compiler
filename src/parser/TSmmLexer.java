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
		ID=1, INT_CONSTANT=2, CHAR_CONSTANT=3, REAL_CONSTANT=4, TRASH=5;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ID", "INT_CONSTANT", "CHAR_CONSTANT", "REAL_CONSTANT", "MANTISSA", "EXPONENT", 
			"TRASH", "WHITES", "ONE_LINE_COMMENT", "MULTI_LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ID", "INT_CONSTANT", "CHAR_CONSTANT", "REAL_CONSTANT", "TRASH"
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
		"\u0004\u0000\u0005w\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0005"+
		"\u0000\u0018\b\u0000\n\u0000\f\u0000\u001b\t\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001 \b\u0001\n\u0001\f\u0001#\t\u0001\u0003\u0001"+
		"%\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u00023\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003:\b\u0003\u0003\u0003<\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004A\b\u0004\n\u0004\f\u0004D\t"+
		"\u0004\u0001\u0004\u0001\u0004\u0004\u0004H\b\u0004\u000b\u0004\f\u0004"+
		"I\u0003\u0004L\b\u0004\u0001\u0005\u0001\u0005\u0003\u0005P\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006W\b"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0004\u0007\\\b\u0007\u000b"+
		"\u0007\f\u0007]\u0001\b\u0001\b\u0001\b\u0001\b\u0005\bd\b\b\n\b\f\bg"+
		"\t\b\u0001\b\u0003\bj\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\tp\b\t"+
		"\n\t\f\ts\t\t\u0001\t\u0001\t\u0001\t\u0002eq\u0000\n\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0000\u000b\u0000\r\u0005\u000f\u0000"+
		"\u0011\u0000\u0013\u0000\u0001\u0000\t\u0003\u0000AZ__az\u0004\u00000"+
		"9AZ__az\u0001\u000019\u0001\u000009\u0002\u0000nntt\u0002\u0000EEee\u0002"+
		"\u0000++--\u0003\u0000\t\n\r\r  \u0001\u0001\n\n\u0081\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0001\u0015\u0001\u0000\u0000\u0000\u0003$\u0001\u0000\u0000"+
		"\u0000\u00052\u0001\u0000\u0000\u0000\u0007;\u0001\u0000\u0000\u0000\t"+
		"K\u0001\u0000\u0000\u0000\u000bM\u0001\u0000\u0000\u0000\rV\u0001\u0000"+
		"\u0000\u0000\u000f[\u0001\u0000\u0000\u0000\u0011_\u0001\u0000\u0000\u0000"+
		"\u0013k\u0001\u0000\u0000\u0000\u0015\u0019\u0007\u0000\u0000\u0000\u0016"+
		"\u0018\u0007\u0001\u0000\u0000\u0017\u0016\u0001\u0000\u0000\u0000\u0018"+
		"\u001b\u0001\u0000\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000\u0019"+
		"\u001a\u0001\u0000\u0000\u0000\u001a\u0002\u0001\u0000\u0000\u0000\u001b"+
		"\u0019\u0001\u0000\u0000\u0000\u001c%\u00050\u0000\u0000\u001d!\u0007"+
		"\u0002\u0000\u0000\u001e \u0007\u0003\u0000\u0000\u001f\u001e\u0001\u0000"+
		"\u0000\u0000 #\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000"+
		"!\"\u0001\u0000\u0000\u0000\"%\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000"+
		"\u0000$\u001c\u0001\u0000\u0000\u0000$\u001d\u0001\u0000\u0000\u0000%"+
		"\u0004\u0001\u0000\u0000\u0000&\'\u0005\'\u0000\u0000\'(\t\u0000\u0000"+
		"\u0000(3\u0005\'\u0000\u0000)*\u0005\'\u0000\u0000*+\u0005\\\u0000\u0000"+
		"+,\u0007\u0004\u0000\u0000,3\u0005\'\u0000\u0000-.\u0005\'\u0000\u0000"+
		"./\u0005\\\u0000\u0000/0\u0003\u0003\u0001\u000001\u0005\'\u0000\u0000"+
		"13\u0001\u0000\u0000\u00002&\u0001\u0000\u0000\u00002)\u0001\u0000\u0000"+
		"\u00002-\u0001\u0000\u0000\u00003\u0006\u0001\u0000\u0000\u000045\u0003"+
		"\u0003\u0001\u000056\u0003\u000b\u0005\u00006<\u0001\u0000\u0000\u0000"+
		"79\u0003\t\u0004\u00008:\u0003\u000b\u0005\u000098\u0001\u0000\u0000\u0000"+
		"9:\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000\u0000;4\u0001\u0000\u0000"+
		"\u0000;7\u0001\u0000\u0000\u0000<\b\u0001\u0000\u0000\u0000=>\u0003\u0003"+
		"\u0001\u0000>B\u0005.\u0000\u0000?A\u0007\u0003\u0000\u0000@?\u0001\u0000"+
		"\u0000\u0000AD\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001"+
		"\u0000\u0000\u0000CL\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000"+
		"EG\u0005.\u0000\u0000FH\u0007\u0003\u0000\u0000GF\u0001\u0000\u0000\u0000"+
		"HI\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000"+
		"\u0000JL\u0001\u0000\u0000\u0000K=\u0001\u0000\u0000\u0000KE\u0001\u0000"+
		"\u0000\u0000L\n\u0001\u0000\u0000\u0000MO\u0007\u0005\u0000\u0000NP\u0007"+
		"\u0006\u0000\u0000ON\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000"+
		"PQ\u0001\u0000\u0000\u0000QR\u0003\u0003\u0001\u0000R\f\u0001\u0000\u0000"+
		"\u0000SW\u0003\u000f\u0007\u0000TW\u0003\u0011\b\u0000UW\u0003\u0013\t"+
		"\u0000VS\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VU\u0001\u0000"+
		"\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0006\u0006\u0000\u0000Y\u000e"+
		"\u0001\u0000\u0000\u0000Z\\\u0007\u0007\u0000\u0000[Z\u0001\u0000\u0000"+
		"\u0000\\]\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000"+
		"\u0000\u0000^\u0010\u0001\u0000\u0000\u0000_`\u0005/\u0000\u0000`a\u0005"+
		"/\u0000\u0000ae\u0001\u0000\u0000\u0000bd\t\u0000\u0000\u0000cb\u0001"+
		"\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000"+
		"ec\u0001\u0000\u0000\u0000fi\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000"+
		"\u0000hj\u0007\b\u0000\u0000ih\u0001\u0000\u0000\u0000j\u0012\u0001\u0000"+
		"\u0000\u0000kl\u0005/\u0000\u0000lm\u0005*\u0000\u0000mq\u0001\u0000\u0000"+
		"\u0000np\t\u0000\u0000\u0000on\u0001\u0000\u0000\u0000ps\u0001\u0000\u0000"+
		"\u0000qr\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000rt\u0001\u0000"+
		"\u0000\u0000sq\u0001\u0000\u0000\u0000tu\u0005*\u0000\u0000uv\u0005/\u0000"+
		"\u0000v\u0014\u0001\u0000\u0000\u0000\u0010\u0000\u0019!$29;BIKOV]eiq"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}