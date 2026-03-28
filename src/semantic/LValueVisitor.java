package semantic;

import ast.expression.*;
import ast.expression.binaryOperation.*;
import ast.expression.unaryOperation.*;
import ast.statement.Assignment;
import ast.statement.Input;
import ast.type.ErrorType;
import visitor.AbstractVisitor;

public class LValueVisitor extends AbstractVisitor<Void,Void> {

    @Override
    public Void visit(Assignment e, Void param) {
        e.getLeft().accept(this,param);
        e.getRigth().accept(this,param);
        if(!e.getLeft().getLValue()){
            new ErrorType("The left side of an assignment must be an lvalue", e.getLeft());
        }
        return null;
    }

    @Override
    public Void visit(Input e, Void param) {
        e.getParameter().accept(this,param);
        if(!e.getParameter().getLValue()){
            new ErrorType("The expression for the input must be lvalue", e.getParameter());
        }
        return null;
    }

    @Override
    public Void visit(Arithmetic e, Void param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Comparison e, Void param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Logic e, Void param) {
        e.getLeft().accept(this,param);
        e.getRight().accept(this,param);
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus e, Void param) {
        e.getOperand().accept(this,param);
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot e, Void param) {
        e.getOperand().accept(this,param);
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(ArrayAccess e, Void param) {
        e.getArray().accept(this,param);
        e.getIndex().accept(this,param);
        e.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Cast e, Void param) {
        e.getCastType().accept(this,param);
        e.getOperand().accept(this,param);
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(CharLiteral e, Void param) {
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess e, Void param) {
        e.getStructureExpression().accept(this,param);
        e.setLValue(true);
        return null;
    }

    @Override
    public Void visit(IntLiteral e, Void param) {
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Invocation e, Void param) {
        e.getFuncName().accept(this,param);
        for(Expression expression : e.getArguments())
            expression.accept(this,param);
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(NumberLiteral e, Void param) {
        e.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Variable e, Void param) {
        e.setLValue(true);
        return null;
    }

}
