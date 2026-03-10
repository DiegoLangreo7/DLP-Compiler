package ast.expression;

import ast.AbstractLocatable;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {

    private Boolean LValue;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    public Boolean getLValue() {
        return LValue;
    }

    public void setLValue(Boolean LValue) {
        this.LValue = LValue;
    }

}
