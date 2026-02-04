package ast.expression;

import ast.AbstractLocatable;

public class Variable extends AbstractLocatable implements Expression {

    private String name;

    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
