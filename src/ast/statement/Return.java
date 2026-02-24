package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;

public class Return extends AbstractLocatable implements Statement {

    private Expression returnValue;

    public Return(int line, int column, Expression returnValue) {
        super(line, column);
        this.returnValue = returnValue;
    }

    public Expression getReturnValue() {
        return returnValue;
    }

    @Override
    public String toString() {
        return "return " + returnValue + ";";
    }
}
