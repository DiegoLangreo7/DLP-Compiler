package ast.expression;

import visitor.Visitor;

public class ArrayAccess extends AbstractExpression {

    private Expression arrayExpression;
    private Expression index;

    public ArrayAccess(int line, int column, Expression array, Expression index) {
        super(line, column);
        this.arrayExpression = array;
        this.index = index;
    }

    public Expression getArray() {
        return arrayExpression;
    }

    public Expression getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return arrayExpression + "[" + index + "]";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
