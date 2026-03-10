package ast.expression.binaryOperation;

import ast.expression.Expression;
import visitor.Visitor;

public class Comparison extends BinaryExpression {

    public Comparison(int line, int column, Expression left, String operator, Expression right) {
        super(line, column, left, operator, right);
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

}
