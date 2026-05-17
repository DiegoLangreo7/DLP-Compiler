package ast.expression;

import visitor.Visitor;

public class TernaryOperator extends AbstractExpression {

    private Expression condition;
    private Expression optLeft;
    private Expression optRight;

    public TernaryOperator(int line, int column, Expression condition, Expression opt1, Expression opt2) {
        super(line, column);
        this.condition = condition;
        this.optLeft = opt1;
        this.optRight = opt2;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getOptRight() {
        return optRight;
    }

    public Expression getOptLeft() {
        return optLeft;
    }

    @Override
    public String toString() {
        return "TernaryOperator{" +
                "condition=" + condition +
                ", optLeft=" + optLeft +
                ", optRight=" + optRight +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
