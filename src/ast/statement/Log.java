package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;

public class Log extends AbstractLocatable implements Statement {

    private Expression parameter;

    public Log(int line, int column, Expression parameter) {
        super(line, column);
        this.parameter = parameter;
    }

    public Expression getParameter() {
        return parameter;
    }
}
