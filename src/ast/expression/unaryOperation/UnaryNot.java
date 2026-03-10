package ast.expression.unaryOperation;

import ast.expression.Expression;
import visitor.Visitor;

public class UnaryNot extends UnaryExpression {

    public UnaryNot(int line, int column, Expression operand) {
        super(line, column, operand);
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

}
