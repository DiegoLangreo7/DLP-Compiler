package ast.expression.binaryOperation;

import ast.expression.Expression;

public class Logic extends BinaryExpression{

    public Logic(int line, int column, Expression left, String operator, Expression right) {
        super(line, column, left, operator, right);
    }
}
