package semantic;

import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.expression.Expression;
import ast.expression.Invocation;
import ast.statement.*;
import ast.type.*;
import visitor.AbstractVisitor;

public class HasReturnVisitor extends AbstractVisitor<Void, Boolean> {
    
    @Override
    public Boolean visit(Assignment e,Void param) {
        return false;
    }

    @Override
    public Boolean visit(IfElse e,Void param) {
        boolean ifPart = false;
        for(Statement statement  : e.getElseBranch())
            ifPart = ifPart || statement.accept(this,param);
        boolean elsePart = false;
        for(Statement statement  : e.getThenBranch())
            elsePart = elsePart || statement.accept(this,param);
        return ifPart && elsePart;
    }

    @Override
    public Boolean visit(Input e,Void param) {
        return false;
    }

    @Override
    public Boolean visit(Log e,Void param) {
        return false;
    }

    @Override
    public Boolean visit(Return e,Void param) {
        return true;
    }

    @Override
    public Boolean visit(While e,Void param) {
        boolean result = false;
        for(Statement statement  : e.getWhileBody())
            result = result || statement.accept(this,param);
        return result;
    }

    @Override
    public Boolean visit(Invocation e, Void param) {
        for(Expression expression : e.getArguments())
            expression.accept(this,param);
        return false;
    }

    @Override
    public Boolean visit(FunctionDefinition e, Void param) {
        Boolean needReturn = e.getType().accept(this,param);
        boolean hasReturn = false;
        for(Statement statement : e.getFuncBody()){
            if(!(statement instanceof VariableDefinition))
                hasReturn = hasReturn || statement.accept(this,param);
        }
        if(needReturn && !hasReturn)
            new ErrorType("In the function " + e.getName() + " not all code paths return a value", e);
        return null;
    }

    @Override
    public Boolean visit(FunctionType e, Void param) {
        return e.getReturnType().accept(this,param);
    }

    @Override
    public Boolean visit(CharType e, Void param) {
        return true;
    }

    @Override
    public Boolean visit(IntType e, Void param) {
        return true;
    }

    @Override
    public Boolean visit(NumberType e, Void param) {
        return true;
    }

    @Override
    public Boolean visit(VoidType e, Void param) {
        return false;
    }

}
