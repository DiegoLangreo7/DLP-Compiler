package ast.statement;

import ast.AbstractLocatable;
import visitor.Visitor;

public class Break extends AbstractLocatable implements Statement {

    public Break(int line, int column) {
        super(line, column);
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
