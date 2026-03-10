package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;
import visitor.Visitor;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(condition).append(") {\n");
        for (Statement stmt : thenPart) {
            sb.append("  ").append(stmt).append("\n");
        }
        sb.append("}");
        if (elsePart != null && !elsePart.isEmpty()) {
            sb.append(" else {\n");
            for (Statement stmt : elsePart) {
                sb.append("  ").append(stmt).append("\n");
            }
            sb.append("}");
        }
        return sb.toString();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

}
