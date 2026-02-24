package ast.expression;

import ast.AbstractLocatable;

public class FieldAccess extends AbstractLocatable implements Expression{

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
}
