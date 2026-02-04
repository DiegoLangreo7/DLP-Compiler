package ast.expression;

import ast.AbstractLocatable;
import ast.type.Type;

public class Cast extends AbstractLocatable implements Expression {

    private Expression operand;
    private Type castType;

    public Cast(int line, int column, Expression operand, Type type) {
        super(line, column);
        this.operand = operand;
        this.castType = type;
    }

    public Expression getOperand() {
        return operand;
    }

    public Type getCastType() {
        return castType;
    }
}
