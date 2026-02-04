package ast.expression;

import ast.AbstractLocatable;

public class FieldAccess extends AbstractLocatable implements Expression{

    private Expression structureExpression;
    private Expression fieldAccess;

    public FieldAccess(int line, int column, Expression structureExpression, Expression fieldAccess) {
        super(line, column);
        this.structureExpression = structureExpression;
        this.fieldAccess = fieldAccess;
    }

    public Expression getStructureExpression() {
        return structureExpression;
    }

    public Expression getFieldAccess() {
        return fieldAccess;
    }
}
