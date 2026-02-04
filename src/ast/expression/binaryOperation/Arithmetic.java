package ast.expression.binaryOperation;

import ast.expression.Expression;

public class Arithmetic extends BinaryExpression {

    public Arithmetic(int line, int column, Expression left, String operator, Expression right) {
        super(line, column, left, operator, right);
    }
}
