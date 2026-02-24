package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;

public class Input extends AbstractLocatable implements Statement {

    private Expression parameter;

    public Input(int line, int column, Expression parameter) {
        super(line, column);
        this.parameter = parameter;
    }

    public Expression getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "input " + parameter + ";";
    }

}
