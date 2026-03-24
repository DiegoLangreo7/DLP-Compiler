package ast.expression;

import ast.AbstractLocatable;
import ast.type.Type;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {

    private Boolean LValue;
    private Type type;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public Boolean getLValue() {
        return LValue;
    }

    @Override
    public void setLValue(Boolean LValue) {
        this.LValue = LValue;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
