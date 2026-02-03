package ast.expression;

import ast.AbstractLocatable;

public class CharLiteral extends AbstractLocatable implements Expression {

    private char value;

    public CharLiteral(int line, int column, char value) {
        super(line, column);
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }
}
