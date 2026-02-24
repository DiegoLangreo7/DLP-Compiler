package ast.expression.binaryOperation;

import ast.AbstractLocatable;
import ast.expression.Expression;

public abstract class BinaryExpression extends AbstractLocatable implements Expression {

    private Expression right;
    private Expression left;
    private String operator;

    public BinaryExpression(int line, int column, Expression left, String operator,Expression right) {
        super(line, column);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getRight() {
        return right;
    }

    public Expression getLeft() {
        return left;
    }

    public String getOperator() {
            return operator;
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }

}
