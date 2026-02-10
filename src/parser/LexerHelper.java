package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String str) {
		try {
			if(str.length()==3) {
				return str.charAt(1);
			}
			else if(str.length() == 4 && str.charAt(1) == '\\') {
				switch (str.charAt(2)) {
					case 'n':
						return '\n';
					case 't':
						return '\t';
				}
			}
			else if(str.length() > 4 && str.charAt(1) == '\\') {
				return (char) Integer.parseInt(str.substring(2, str.length() - 1),8);
			}
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		throw new IllegalStateException();
	}

	public static double lexemeToReal(String str) {
		try {
			return Double.parseDouble(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

}
