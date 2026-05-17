package codegen;

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
import visitor.Visitor;

public abstract class AbstractCGVisitor<TP,TR> implements Visitor<TP,TR> {

    protected  CodeGenerator cg;

    @Override
    public TR visit(Program e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(FunctionDefinition e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(VariableDefinition e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Arithmetic e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Comparison e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Logic e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(UnaryMinus e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(UnaryNot e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(ArrayAccess e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Cast e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(CharLiteral e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(FieldAccess e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(IntLiteral e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Invocation e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(NumberLiteral e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Variable e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Assignment e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(IfElse e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Input e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Log e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(Return e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(While e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(For e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(ArrayType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(CharType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(ErrorType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(FunctionType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(IntType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(NumberType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(RecordField e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(RecordType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }

    @Override
    public TR visit(VoidType e, TP param) {
        throw new UnsupportedOperationException(e.toString() + "don't support that visitor operation");
    }
}
