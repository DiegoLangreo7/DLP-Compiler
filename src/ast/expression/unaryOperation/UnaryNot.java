package ast.expression.unaryOperation;

import ast.expression.Expression;

public class UnaryNot extends UnaryExpression {

    public UnaryNot(int line, int column, Expression operand) {
        super(line, column, operand);
    }
}
