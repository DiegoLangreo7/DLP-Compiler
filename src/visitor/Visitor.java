package visitor;

import ast.Program;
import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.expression.*;
import ast.expression.binaryOperation.Arithmetic;
import ast.expression.binaryOperation.Comparison;
import ast.expression.binaryOperation.Logic;
import ast.expression.unaryOperation.UnaryMinus;
import ast.expression.unaryOperation.UnaryNot;
import ast.statement.*;
import ast.type.*;

public interface Visitor <TP,TR> {

    /**
     * Para todos:
     * 1. recorrer los hijos
     * 2. calcular atributos
     */

    //Program
    TR visit (Program e, TP param);

    //Definition
    TR visit (FunctionDefinition e, TP param);
    TR visit (VariableDefinition e, TP param);

    //Expression
    TR visit (Arithmetic e, TP param);
    TR visit (Comparison e, TP param);
    TR visit (Logic e, TP param);
    TR visit (UnaryMinus e, TP param);
    TR visit (UnaryNot e, TP param);
    TR visit (ArrayAccess e, TP param);
    TR visit (Cast e, TP param);
    TR visit (CharLiteral e, TP param);
    TR visit (FieldAccess e, TP param);
    TR visit (IntLiteral e, TP param);
    TR visit (Invocation e, TP param);
    TR visit (NumberLiteral e, TP param);
    TR visit (Variable e, TP param);

    //Statement
    TR visit(CompoundAssignment compoundAssignment, TP param);
    TR visit (Assignment e, TP param);
    TR visit (IfElse e, TP param);
    TR visit (Input e, TP param);
    TR visit (Log e, TP param);
    TR visit (Return e, TP param);
    TR visit (While e, TP param);

    //Type
    TR visit (ArrayType e, TP param);
    TR visit (CharType e, TP param);
    TR visit (ErrorType e, TP param);
    TR visit (FunctionType e, TP param);
    TR visit (IntType e, TP param);
    TR visit (NumberType e, TP param);
    TR visit (RecordField e, TP param);
    TR visit (RecordType e, TP param);
    TR visit (VoidType e, TP param);

}
