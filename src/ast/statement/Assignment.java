package ast.statement;

import ast.AbstractLocatable;
import ast.expression.Expression;

public class Assignment extends AbstractLocatable implements Statement {

    private Expression left;
    private Expression rigth;

    public Assignment(int line, int column, Expression left, Expression rigth) {
        super(line, column);
        this.left = left;
        this.rigth = rigth;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRigth() {
        return rigth;
    }


}
