package visitor;

import ast.Program;
import ast.definition.Definition;
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

public class AbstractVisitor<TP, TR> implements Visitor<TP, TR>{

    @Override
    public TR visit(Program e, TP param) {
        for(Definition definition : e.getDefinitions())
            definition.accept(this,param);
        return null;
    }

    @Override
    public TR visit(FunctionDefinition e, TP param) {
        e.getType().accept(this,param);
        for(Statement statement : e.getFuncBody())
            statement.accept(this,param);
        return null;
    }

    @Override
    public TR visit(VariableDefinition e, TP param) {
        e.getType().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Arithmetic e, TP param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Comparison e, TP param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Logic e, TP param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        return null;
    }

    @Override
    public TR visit(UnaryMinus e, TP param) {
        e.getOperand().accept(this,param);
        return null;
    }

    @Override
    public TR visit(UnaryNot e, TP param) {
        e.getOperand().accept(this,param);
        return null;
    }

    @Override
    public TR visit(ArrayAccess e, TP param) {
        e.getArray().accept(this,param);
        e.getIndex().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Cast e, TP param) {
        e.getCastType().accept(this,param);
        e.getOperand().accept(this,param);
        return null;
    }

    @Override
    public TR visit(CharLiteral e, TP param) {
        return null;
    }

    @Override
    public TR visit(FieldAccess e, TP param) {
        e.getStructureExpression().accept(this,param);
        return null;
    }

    @Override
    public TR visit(IntLiteral e, TP param) {
        return null;
    }

    @Override
    public TR visit(Invocation e, TP param) {
        e.getFuncName().accept(this,param);
        for(Expression expression : e.getArguments())
                expression.accept(this,param);
        return null;
    }

    @Override
    public TR visit(NumberLiteral e, TP param) {
        return null;
    }

    @Override
    public TR visit(Variable e, TP param) {
        return null;
    }

    @Override
    public TR visit(Assignment e, TP param) {
        e.getLeft().accept(this,param);
        e.getRigth().accept(this,param);
        return null;
    }

    @Override
    public TR visit(IfElse e, TP param) {
        e.getCondition().accept(this,param);
        for(Statement statement  : e.getThenBranch())
            statement.accept(this,param);
        for(Statement statement  : e.getElseBranch())
            statement.accept(this,param);
        return null;
    }

    @Override
    public TR visit(Input e, TP param) {
        e.getParameter().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Log e, TP param) {
        e.getParameter().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Return e, TP param) {
        if(e.getReturnValue()!=null)
            e.getReturnValue().accept(this,param);
        return null;
    }

    @Override
    public TR visit(While e, TP param) {
        e.getCondition().accept(this,param);
        for(Statement statement  : e.getWhileBody())
            statement.accept(this,param);
        return null;
    }

    @Override
    public TR visit(ArrayType e, TP param) {
        e.getTypeOf().accept(this,param);
        return null;
    }

    @Override
    public TR visit(CharType e, TP param) {
        return null;
    }

    @Override
    public TR visit(ErrorType e, TP param) {
        return null;
    }

    @Override
    public TR visit(FunctionType e, TP param) {
        e.getReturnType().accept(this,param);
        for(VariableDefinition variableDefinition  : e.getParameters())
            variableDefinition.accept(this,param);
        return null;
    }

    @Override
    public TR visit(IntType e, TP param) {
        return null;
    }

    @Override
    public TR visit(NumberType e, TP param) {
        return null;
    }

    @Override
    public TR visit(RecordField e, TP param) {
        e.getFieldType().accept(this,param);
        return null;
    }

    @Override
    public TR visit(RecordType e, TP param) {
        for(RecordField field : e.getFields())
            field.accept(this,param);
        return null;
    }

    @Override
    public TR visit(VoidType e, TP param) {
        return null;
    }

}
