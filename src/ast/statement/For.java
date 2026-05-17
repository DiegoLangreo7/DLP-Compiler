package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;
import visitor.Visitor;

import java.util.List;

public class For extends AbstractLocatable implements Statement{

    private Statement initialization;
    private Expression condition;
    private Statement increment;
    private List<Statement> body;

    public For(int line, int column, Statement initialization, Expression condition, Statement increment, List<Statement> body) {
        super(line, column);
        this.initialization = initialization;
        this.condition = condition;
        this.increment = increment;
        this.body = body;
    }

    public Statement getInitialization() {
        return initialization;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getIncrement() {
        return increment;
    }

    public List<Statement> getBody() {
        return body;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
