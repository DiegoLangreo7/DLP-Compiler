package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;

import java.util.List;

public class While extends AbstractLocatable implements Statement {

    private Expression condition;
    private List<Statement> whileBody;

    public While(int line, int column, Expression condition, List<Statement> whileBody) {
        super(line, column);
        this.condition = condition;
        this.whileBody = whileBody;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getWhileBody() {
        return whileBody;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("while (").append(condition).append(") {\n");
        for (Statement stmt : whileBody) {
            sb.append("  ").append(stmt).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
