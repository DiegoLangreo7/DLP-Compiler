package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;
import visitor.Visitor;

import java.util.List;

public class Case extends AbstractLocatable {

    private Expression toCompare;
    private List<Statement> statements;

    public Case(int line, int column,  Expression expression, List<Statement> statements) {
        super(line, column);
        this.toCompare = expression;
        this.statements = statements;
    }

    public Expression getToCompare() {
        return toCompare;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
