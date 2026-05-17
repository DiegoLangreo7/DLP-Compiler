package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;
import visitor.Visitor;

import java.util.List;

public class DoWhile extends AbstractLocatable implements Statement {

    private List<Statement> body;
    private Expression condition;
    public DoWhile(int line, int column, List<Statement> body, Expression condition) {
        super(line, column);
        this.body = body;
        this.condition = condition;
    }

    public List<Statement> getBody() {return body;}

    public Expression getCondition() {return condition;}

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return  visitor.visit(this, param);
    }
}
