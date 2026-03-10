package ast.expression;

import visitor.Visitor;

public class FieldAccess extends AbstractExpression {

    private Expression structureExpression;
    private String fieldAccess;

    public FieldAccess(int line, int column, Expression structureExpression, String fieldAccess) {
        super(line, column);
        this.structureExpression = structureExpression;
        this.fieldAccess = fieldAccess;
    }

    public Expression getStructureExpression() {
        return structureExpression;
    }

    public String getFieldAccess() {
        return fieldAccess;
    }

    @Override
    public String toString() {
        return structureExpression + "." + fieldAccess;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

}
