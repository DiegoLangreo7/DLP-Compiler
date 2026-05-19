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
    public Boolean visit(Assignment e, Void param) {
        return false;
    }

    @Override
    public Boolean visit(IfElse e, Void param) {
        boolean thenHasReturn = false;
        if (e.getThenBranch() != null) {
            for (Statement s : e.getThenBranch()) {
                Boolean val = s.accept(this, param);
                if (val != null && val) thenHasReturn = true;
            }
        }

        boolean elseHasReturn = false;
        if (e.getElseBranch() != null && !e.getElseBranch().isEmpty()) {
            for (Statement s : e.getElseBranch()) {
                Boolean val = s.accept(this, param);
                if (val != null && val) elseHasReturn = true;
            }
        }

        return thenHasReturn && elseHasReturn;
    }

    @Override
    public Boolean visit(Input e, Void param) {
        return false;
    }

    @Override
    public Boolean visit(Log e, Void param) {
        return false;
    }

    @Override
    public Boolean visit(Return e, Void param) {
        return true;
    }

    @Override
    public Boolean visit(Continue e, Void param) {
        return false;
    }

    @Override
    public Boolean visit(Break e, Void param) {
        return false;
    }

    @Override
    public Boolean visit(While e, Void param) {
        if (e.getWhileBody() != null) {
            for (Statement statement : e.getWhileBody()) {
                statement.accept(this, param);
            }
        }
        return false;
    }

    @Override
    public Boolean visit(Invocation e, Void param) {
        if (e.getArguments() != null) {
            for (Expression expression : e.getArguments()) {
                expression.accept(this, param);
            }
        }
        return false;
    }

    @Override
    public Boolean visit(FunctionDefinition e, Void param) {
        Boolean needReturn = e.getType().accept(this, param);
        boolean functionFlwReturned = false;

        if (e.getFuncBody() != null) {
            for (Statement statement : e.getFuncBody()) {
                if (!(statement instanceof VariableDefinition)) {
                    Boolean stmtRet = statement.accept(this, param);
                    if (stmtRet != null && stmtRet) {
                        functionFlwReturned = true;
                    }
                }
            }
        }

        if (needReturn != null && needReturn && !functionFlwReturned) {
            new ErrorType("In the function " + e.getName() + " not all code paths return a value", e);
        }
        return null;
    }

    @Override
    public Boolean visit(FunctionType e, Void param) {
        return e.getReturnType().accept(this, param);
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

    @Override
    public Boolean visit(ErrorType e, Void param) {
        return false;
    }
}