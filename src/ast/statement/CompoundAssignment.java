package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;
import visitor.Visitor;

public class CompoundAssignment extends AbstractLocatable implements Statement {

    private Expression left;
    private Expression rigth;
    private String operator;

    public CompoundAssignment(int line, int column, Expression left, Expression right, String operator) {
        super(line, column);
        this.left = left;
        this.rigth = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getRigth() {
        return rigth;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
