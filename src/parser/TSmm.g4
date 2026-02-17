grammar TSmm;	

// ----- REGLAS SINTACTICAS ------

program: ( variableDefinition | functionDefinition )* mainFunction EOF
       ;

variableDefinition: 'let' ID ( ',' ID )* ':' type ';'
                  ;

functionDefinition: 'function' ID '(' ( ID ':' type ( ',' ID ':' type)* )? ')' ':' type '{' variableDefinition* statement* '}'
                  ;

mainFunction: 'function' 'main' '(' ')' ':' 'void' '{' variableDefinition* statement* '}'
                  ;

type: 'int'
    | 'number'
    | 'char'
    | 'void'
    | '[' INT_CONSTANT ']' type
    | '[' variableDefinition+ ']'
    ;

statement: 'log' expression ( ',' expression )* ';'
         | 'input' expression ( ',' expression )* ';'
         | expression '=' expression ';'
         | 'if' '(' expression ')' body ( 'else' body )?
         | 'while' '(' expression ')' body
         | 'return' expression ';'
         | ID '(' ( expression ( ',' expression )* )? ')' ';'
         ;

body: '{' statement* '}'
    | statement
    ;

expression: ID
          | INT_CONSTANT
          | REAL_CONSTANT
          | CHAR_CONSTANT
          | ID '(' ( expression ( ',' expression )* )? ')'
          | '(' expression ')'
          | expression '[' expression ']'
          | expression '.' ID
          | '(' expression 'as' type ')'
          | '-' expression
          | '!' expression
          | expression ('*' | '/' | '%' ) expression
          | expression ( '+' | '-' ) expression
          | expression ( '>' | '>=' | '>' | '>=' | '<' | '<=' | '!=' | '==' ) expression
          | expression ( '&&' | '||' ) expression
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

