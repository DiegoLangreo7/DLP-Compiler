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

program returns [Program ast = new Program()] locals [List<Definition> definitions = new ArrayList<Definition>()]:
        ( vd = variableDefinition {$definitions.addAll($vd.ast); } |  fd = functionDefinition { $definitions.add($fd.ast); })* mainFunction { $definitions.add($mainFunction.ast); } EOF
        { $ast.addDefinitions($definitions); }
       ;

variableDefinition returns [List<VariableDefinition> ast = new ArrayList<VariableDefinition>()] locals [List<String> varNames = new ArrayList<String>()]:
     FS = 'let' ID1 = ID { $varNames.add($ID1.text); }( ',' IDL = ID { $varNames.add( $IDL.text); })* ':' type ';'
     { for (String varName : $varNames) { $ast.add(new VariableDefinition($FS.getLine(), $FS.getCharPositionInLine()+1, varName, $type.ast)); } }
    ;

functionDefinition returns [FunctionDefinition ast] locals [List<VariableDefinition> params = new ArrayList<VariableDefinition>(), List<Statement> funcBody = new ArrayList<Statement>(), Type returnType]:
    FS = 'function' FUNCNAME = ID '(' ( ID1 = ID ':' t1 = type { $params.add( new VariableDefinition($FS.getLine(), $FS.getCharPositionInLine()+1,$ID1.text, $t1.ast));} ( ',' IDL = ID ':' tl = type { $params.add( new VariableDefinition($FS.getLine(), $FS.getCharPositionInLine()+1,$IDL.text, $tl.ast));})* )? ')' ':' ( (rType = type {$returnType = $rType.ast;}) | 'void' {$returnType = VoidType.getInstance();}) '{' (variableDefinition { $funcBody.addAll($variableDefinition.ast);})* ( statement { $funcBody.addAll($statement.ast); })* '}'
    { $ast = new FunctionDefinition($FS.getLine(), $FS.getCharPositionInLine()+1, $FUNCNAME.text, new FunctionType($returnType, $params ), $funcBody); }
    ;

mainFunction  returns [FunctionDefinition ast] locals [List<VariableDefinition> params = new ArrayList<VariableDefinition>(), List<Statement> funcBody = new ArrayList<Statement>()]:
    FS = 'function' FUNCNAME = 'main' '(' ')' ':' 'void' '{' (variableDefinition { $funcBody.addAll($variableDefinition.ast);})* ( statement { $funcBody.addAll($statement.ast); })* '}'
    { $ast = new FunctionDefinition($FS.getLine(), $FS.getCharPositionInLine()+1, $FUNCNAME.text, new FunctionType(VoidType.getInstance(), $params), $funcBody); }
    ;

type returns [Type ast] locals [List<VariableDefinition> records = new ArrayList<VariableDefinition>()]:
      simpleType
      { $ast = $simpleType.ast; }

    | '[' INT = INT_CONSTANT ']' type
      { $ast = new ArrayType(LexerHelper.lexemeToInt($INT.text), $type.ast); }

    | '[' ( vd = variableDefinition {$records.addAll($vd.ast);} )+ ']'
      { $ast = new RecordType($records); }
    ;

simpleType returns [Type ast]:
    'int'
    { $ast = IntType.getInstance(); }

  | 'number'

    { $ast = NumberType.getInstance(); }
  | 'char'

    { $ast = CharType.getInstance(); }
  ;

statement returns [List<Statement> ast = new ArrayList<Statement>()] locals [List<Statement> else = new ArrayList<Statement>()]:
           FS = 'log' e1 = expression { $ast.add(new Log($FS.getLine(), $FS.getCharPositionInLine()+1,$e1.ast));} ( ',' el = expression { $ast.add(new Log($FS.getLine(), $FS.getCharPositionInLine()+1,$el.ast));})* ';'

         | FS = 'input' e1 = expression { $ast.add(new Input($FS.getLine(), $FS.getCharPositionInLine()+1,$e1.ast));}( ',' el = expression { $ast.add(new Input($FS.getLine(), $FS.getCharPositionInLine()+1,$el.ast));} )* ';'

         | e1 = expression '=' e2 = expression ';'
           { $ast.add(new Assignment($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast)); }

         | FS = 'if' '(' expression ')' thenPart = body ( 'else' elsePart = body { $else = $elsePart.ast; })?
           { $ast.add(new IfElse($FS.getLine(), $FS.getCharPositionInLine()+1, $expression.ast, $thenPart.ast, $else)); }

         | FS = 'while' '(' expression ')' body
           { $ast.add(new While($FS.getLine(), $FS.getCharPositionInLine()+1, $expression.ast, $body.ast)); }

         | FS = 'return' expression ';'
           { $ast.add(new Return($FS.getLine(), $FS.getCharPositionInLine()+1, $expression.ast)); }

         | invocation ';'
           { $ast.add($invocation.ast); }
         ;

body returns [List<Statement> ast = new ArrayList<Statement>()]:
      '{' ( statement { $ast.addAll($statement.ast); })* '}'
    | statement { $ast.addAll($statement.ast); }
    ;

invocation returns [Invocation ast] locals [List<Expression> params = new ArrayList<Expression>()]:
    ID '(' ( e1 = expression { $params.add($e1.ast); }( ',' el = expression { $params.add($el.ast); })* )? ')'
    { $ast = new Invocation($ID.getLine(), $ID.getCharPositionInLine()+1, new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), $params); }
    ;

expression returns [Expression ast] locals [List<Expression> params = new ArrayList<Expression>()]:
            ID  // Variable
            { $ast = new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text); }

          | INT = INT_CONSTANT  // IntLiteral
            { $ast = new IntLiteral($INT.getLine(), $INT.getCharPositionInLine()+1, LexerHelper.lexemeToInt($INT.text)); }

          | REAL = REAL_CONSTANT    // RealLiteral
            { $ast = new NumberLiteral($REAL.getLine(), $REAL.getCharPositionInLine()+1, LexerHelper.lexemeToReal($REAL.text)); }

          | CHAR = CHAR_CONSTANT    // CharLiteral
            { $ast = new CharLiteral($CHAR.getLine(), $CHAR.getCharPositionInLine()+1, LexerHelper.lexemeToChar($CHAR.text)); }

          | invocation  // Invocation
            { $ast = $invocation.ast; }

          | '(' e1 = expression ')' // ParenthesizedExpression
            { $ast = $e1.ast; }

          | e1 = expression '[' e2 = expression ']' // ArrayAccess
            { $ast = new ArrayAccess($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $e2.ast); }

          | e1 = expression '.' ID // FieldAccess
            { $ast = new FieldAccess($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $ID.text); }

          | FS = '(' e1 = expression 'as' simpleType ')'  // Cast
            { $ast = new Cast($FS.getLine(), $FS.getCharPositionInLine()+1, $e1.ast, $simpleType.ast); }

          | FS = '-' e1 = expression // UnaryMinus
            { $ast = new UnaryMinus($FS.getLine(), $FS.getCharPositionInLine()+1, $e1.ast); }

          | FS = '!' e1 = expression // UnaryNot
            { $ast = new UnaryNot($FS.getLine(), $FS.getCharPositionInLine()+1, $e1.ast); }

          | e1 = expression OP = ('*' | '/' | '%' ) e2 = expression // BinaryArithmetic
            { $ast = new Arithmetic($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $OP.text, $e2.ast); }

          | e1 = expression OP = ( '+' | '-' ) e2 = expression // BinaryArithmetic
            { $ast = new Arithmetic($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $OP.text, $e2.ast); }

          | e1 = expression OP = ( '>' | '>=' | '>' | '>=' | '<' | '<=' | '!=' | '==' ) e2 = expression // Comparison
            { $ast = new Comparison($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $OP.text, $e2.ast); }

          | e1 = expression OP = ( '&&' | '||' ) e2 = expression // Logic
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

