grammar TSmm;	

// ----- REGLAS SINTACTICAS ------

program: expression* EOF
       ;

expression: ID
          | INT_CONSTANT
          | REAL_CONSTANT
          | CHAR_CONSTANT
          | '(' expression ')'
          | expression '[' expression ']'
          |
          | expression ('+' | '-' | '*' | '/' ) expression
          ;

// ----- REGLAS LEXICAS ------

ID: [_a-zA-Z][_a-zA-Z0-9]*
  ;

INT_CONSTANT: '0' | [1-9][0-9]*
            ;

CHAR_CONSTANT: ( '\'' . '\'' )
             | ( '\'' '\\' [nt] '\'')
             | ( '\'' '\\' INT_CONSTANT '\'')
             ;

REAL_CONSTANT: ( INT_CONSTANT EXPONENT ) | ( MANTISSA EXPONENT? )
             ;
fragment
MANTISSA: INT_CONSTANT '.' [0-9]* | '.' [0-9]+
        ;

fragment
EXPONENT: [Ee] [-+]? INT_CONSTANT
        ;

TRASH: ( WHITES | ONE_LINE_COMMENT | MULTI_LINE_COMMENT ) -> skip
     ;

fragment
WHITES : [\n\r\t ]+
       ;

fragment
ONE_LINE_COMMENT: '//' .*? ('\n' | EOF)
                ;
fragment
MULTI_LINE_COMMENT: '/*' .*? '*/'
                  ;

