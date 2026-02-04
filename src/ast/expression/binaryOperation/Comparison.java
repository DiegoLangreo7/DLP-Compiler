package ast.expression.binaryOperation;

import ast.expression.Expression;

public class Comparison extends BinaryExpression {

    public Comparison(int line, int column, Expression left, String operator, Expression right) {
        super(line, column, left, operator, right);
    }
}
