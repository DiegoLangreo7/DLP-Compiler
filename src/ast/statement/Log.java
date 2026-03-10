package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;
import visitor.Visitor;

public class Log extends AbstractLocatable implements Statement {

    private Expression parameter;

    public Log(int line, int column, Expression parameter) {
        super(line, column);
        this.parameter = parameter;
    }

    public Expression getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "log " + parameter + ";";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
