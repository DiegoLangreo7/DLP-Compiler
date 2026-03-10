package ast.expression.unaryOperation;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public abstract class UnaryExpression extends AbstractExpression {

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
