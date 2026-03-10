package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression {

    private Expression operand;
    private Type castType;

    public Cast(int line, int column, Expression operand, Type type) {
        super(line, column);
        this.operand = operand;
        this.castType = type;
    }

    public Expression getOperand() {
        return operand;
    }

    public Type getCastType() {
        return castType;
    }

    @Override
    public String toString() {
        return "(" + operand + " as " + castType + ")";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

}
