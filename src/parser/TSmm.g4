grammar TSmm;	

// ----- IMPORTS ------

@header{
import ast.*;
import ast.definition.*;
import ast.expression.*;
import ast.expression.*;
import ast.expression.binaryOperation.*;
import ast.expression.unaryOperation.*;
import ast.statement.*;
import ast.type.*;
}

// ----- REGLAS SINTACTICAS ------

program returns [Program ast = new Program()]:
        ( vd = variableDefinition {$ast.addDefinition($vd.ast);} |  fd = functionDefinition {$ast.addDefinition($fd.ast);})* EOF
       ;

variableDefinition returns [List<VariableDefinition> ast]:
     'let' ID ( ',' ID )* ':' type ';'
    ;

functionDefinition returns [FunctionDefinition ast]:
    'function' ID '(' ( ID ':' type ( ',' ID ':' type)* )? ')' ':' type '{' variableDefinition* statement* '}'
    ;

type returns [Type ast] locals [List<VariableDefinition> records = new ArrayList<VariableDefinition>()]:
      'int'
      {$ast = IntType.getInstance();}
    | 'number'
      {$ast = NumberType.getInstance();}
    | 'char'
      {$ast = CharType.getInstance();}
    | 'void'
      {$ast = VoidType.getInstance();}
    | '[' INT = INT_CONSTANT ']' type
      {$ast = new ArrayType(LexerHelper.lexemeToInt($INT.text), $type.ast);}
    | '[' ( vd = variableDefinition {$records.addAll($vd.ast);} )+ ']'
      {$ast = new RecordType($records);}
    ;

statement returns [Statement ast]:
           'log' expression ( ',' expression )* ';'
         | 'input' expression ( ',' expression )* ';'
         | expression '=' expression ';'
         | 'if' '(' expression ')' body ( 'else' body )?
         | 'while' '(' expression ')' body
         | 'return' expression ';'
            {}
         | ID '(' ( expression ( ',' expression )* )? ')' ';'
         ;

body: '{' statement* '}'
    | statement
    ;

expression returns [Expression ast] locals [List<Expression> params = new ArrayList<Expression>()]:
            ID
            { $ast = new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text); }

          | INT = INT_CONSTANT
            { $ast = new IntLiteral($INT.getLine(), $INT.getCharPositionInLine()+1, LexerHelper.lexemeToInt($INT.text)); }

          | REAL = REAL_CONSTANT
            { $ast = new NumberLiteral($REAL.getLine(), $REAL.getCharPositionInLine()+1, LexerHelper.lexemeToReal($REAL.text)); }

          | CHAR = CHAR_CONSTANT
            { $ast = new CharLiteral($CHAR.getLine(), $CHAR.getCharPositionInLine()+1, LexerHelper.lexemeToChar($CHAR.text)); }

          | ID '(' ( e1 = expression { $params.add($e1.ast); }( ',' el = expression { $params.add($el.ast); })* )? ')'
            { $ast = new Invocation($ID.getLine(), $ID.getCharPositionInLine()+1, new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), $params); }

          | '(' e1 = expression ')'
            { $ast = $e1.ast; }

          | e1 = expression '[' e2 = expression ']'
            { $ast = new ArrayAccess($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast); }

          | e1 = expression '.' ID
            { $ast = new FieldAccess($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $ID.text); }

          | FS = '(' e1 = expression 'as' type ')'
            { $ast = new Cast($FS.getLine(), $FS.getCharPositionInLine()+1, $e1.ast, null); }

          | FS = '-' e1 = expression
            { $ast = new UnaryMinus($FS.getLine(), $FS.getCharPositionInLine()+1, $e1.ast); }

          | FS = '!' e1 = expression
            { $ast = new UnaryNot($FS.getLine(), $FS.getCharPositionInLine()+1, $e1.ast); }

          | e1 = expression OP = ('*' | '/' | '%' ) e2 = expression
            { $ast = new Arithmetic($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $OP.text, $e2.ast); }

          | e1 = expression OP = ( '+' | '-' ) e2 = expression
            { $ast = new Arithmetic($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $OP.text, $e2.ast); }

          | e1 = expression OP = ( '>' | '>=' | '>' | '>=' | '<' | '<=' | '!=' | '==' ) e2 = expression
            { $ast = new Comparison($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $OP.text, $e2.ast); }

          | e1 = expression OP = ( '&&' | '||' ) e2 = expression
            { $ast = new Logic($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $OP.text, $e2.ast); }
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

