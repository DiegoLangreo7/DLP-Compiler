package ast.expression;

import ast.AbstractLocatable;

public class Cast extends AbstractLocatable implements Expression {

    private Expression operand;
    private Type type;

    public Cast(int line, int column) {
        super(line, column);
    }
}
