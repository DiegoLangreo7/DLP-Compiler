package ast.expression;

import ast.AbstractLocatable;

public class NumberLiteral extends AbstractLocatable implements Expression {

    private double value;

    public NumberLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
