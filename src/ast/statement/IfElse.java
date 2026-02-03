package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;

import java.util.List;

public class IfElse extends AbstractLocatable implements Statement {

    private Expression condition;
    private List<Statement> thenPart;
    private List<Statement> elsePart;

    public IfElse(int line, int column, Expression condition, List<Statement> thenPart, List<Statement> elsePart) {
        super(line, column);
        this.condition = condition;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getThenBranch() {
        return thenPart;
    }

    public List<Statement> getElseBranch() {
        return elsePart;
    }
}
