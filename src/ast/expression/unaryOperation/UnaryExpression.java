package ast.expression.unaryOperation;

import ast.AbstractLocatable;
import ast.expression.Expression;

public abstract class UnaryExpression extends AbstractLocatable implements Expression {

    private Expression operand;

    public UnaryExpression(int line, int column, Expression operand) {
        super(line, column);
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + operand + ")";
    }
}
