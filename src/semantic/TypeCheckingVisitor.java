package semantic;

import ast.definition.FunctionDefinition;
import ast.expression.*;
import ast.expression.binaryOperation.Arithmetic;
import ast.expression.binaryOperation.Comparison;
import ast.expression.binaryOperation.Logic;
import ast.expression.unaryOperation.UnaryMinus;
import ast.expression.unaryOperation.UnaryNot;
import ast.statement.*;
import ast.type.*;
import visitor.AbstractVisitor;

import java.util.List;

public class TypeCheckingVisitor extends AbstractVisitor<Type, Void> {

    @Override
    public Void visit(FunctionDefinition e, Type param) {
        e.getType().accept(this,param);
        for(Statement statement : e.getFuncBody())
            statement.accept(this,((FunctionType) e.getType()).getReturnType());
        return null;
    }

    @Override
    public Void visit(Arithmetic e, Type param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        e.setType(e.getLeft().getType().arithmetic(e.getRight().getType(), e));
        return null;
    }

    @Override
    public Void visit(Comparison e, Type param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        e.setType(e.getLeft().getType().comparison(e.getRight().getType(), e));
        return null;
    }

    @Override
    public Void visit(Logic e, Type param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        e.setType(e.getLeft().getType().logic(e.getRight().getType(), e));
        return null;
    }

    @Override
    public Void visit(UnaryMinus e, Type param) {
        e.getOperand().accept(this,param);
        e.setType(e.getOperand().getType().arithmetic(e));
        return null;
    }

    @Override
    public Void visit(UnaryNot e, Type param) {
        e.getOperand().accept(this,param);
        e.setType(e.getOperand().getType().logic(e));
        return null;
    }

    @Override
    public Void visit(ArrayAccess e, Type param) {
        e.getArray().accept(this,param);
        e.getIndex().accept(this,param);
        e.setType(e.getArray().getType().squareBrackets(e.getIndex().getType(), e));
        return null;
    }

    @Override
    public Void visit(Cast e, Type param) {
        e.getCastType().accept(this,param);
        e.getOperand().accept(this,param);
        e.setType(e.getOperand().getType().canBeCastTo(e.getCastType(),e));
        return null;
    }

    @Override
    public Void visit(CharLiteral e, Type param) {
        e.setType(CharType.getInstance());
        return null;
    }

    @Override
    public Void visit(FieldAccess e, Type param) {
        e.getStructureExpression().accept(this,param);
        e.setType(e.getStructureExpression().getType().dot(e.getFieldAccess(), e));
        return null;
    }

    @Override
    public Void visit(IntLiteral e, Type param) {
        e.setType(IntType.getInstance());
        return null;
    }

    @Override
    public Void visit(Invocation e, Type param) {
        e.getFuncName().accept(this,param);
        for(Expression expression : e.getArguments())
            expression.accept(this,param);
        List<Type> argumentTypes = e.getArguments().stream().map(Expression::getType).toList();
        e.setType(e.getFuncName().getType().parenthesis(argumentTypes, e));
        return null;
    }

    @Override
    public Void visit(NumberLiteral e, Type param) {
        e.setType(NumberType.getInstance());
        return null;
    }

    @Override
    public Void visit(Variable e, Type param) {
        e.setType(e.getDefinition().getType());
        return null;
    }

    @Override
    public Void visit(Assignment e, Type param) {
        e.getLeft().accept(this,param);
        e.getRigth().accept(this,param);
        e.getRigth().getType().mustPromotesTo(e.getLeft().getType(), e);
        return null;
    }

    @Override
    public Void visit(IfElse e, Type param) {
        e.getCondition().accept(this,param);
        for(Statement statement  : e.getElseBranch())
            statement.accept(this,param);
        for(Statement statement  : e.getThenBranch())
            statement.accept(this,param);
        e.getCondition().getType().mustBeLogical(e);
        return null;
    }

    @Override
    public Void visit(Input e, Type param) {
        e.getParameter().accept(this,param);
        e.getParameter().getType().mustBeBuiltIn(e);
        return null;
    }

    @Override
    public Void visit(Log e, Type param) {
        e.getParameter().accept(this,param);
        e.getParameter().getType().mustBeBuiltIn(e);
        return null;
    }

    @Override
    public Void visit(Return e, Type param) {
        e.getReturnValue().accept(this,param);
        e.getReturnValue().getType().mustPromotesTo(param, e);
        return null;
    }

    @Override
    public Void visit(While e, Type param) {
        e.getCondition().accept(this,param);
        for(Statement statement  : e.getWhileBody())
            statement.accept(this,param);
        e.getCondition().getType().mustBeLogical(e);
        return null;
    }

}
