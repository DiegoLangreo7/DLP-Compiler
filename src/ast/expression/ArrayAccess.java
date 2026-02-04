package ast.expression;

import ast.AbstractLocatable;

public class ArrayAccess extends AbstractLocatable implements Expression {

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
}
