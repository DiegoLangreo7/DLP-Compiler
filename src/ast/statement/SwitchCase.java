package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;
import visitor.Visitor;

import java.util.List;

public class SwitchCase extends AbstractLocatable implements Statement{

    private Expression expression;
    private List<Case> cases;

    public SwitchCase(int line, int column, Expression expression, List<Case> cases) {
        super(line, column);
        this.expression = expression;
        this.cases = cases;
    }

    public List<Case> getCases() {
        return cases;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
