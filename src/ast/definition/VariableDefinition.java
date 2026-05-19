package ast.definition;

import ast.expression.Expression;
import ast.statement.Statement;
import ast.type.Type;
import visitor.Visitor;

public class VariableDefinition extends AbstractDefinition implements Statement {

    private int offset;
    private Expression expression;

    public VariableDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

    public VariableDefinition(int line, int column, String name, Type type,  Expression expression) {
        super(line, column, name, type);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return getName() + ": " + getType() + ";";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Expression getExpression() {
        return expression;
    }

}
