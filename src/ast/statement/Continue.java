package ast.statement;

import ast.AbstractLocatable;
import visitor.Visitor;

public class Continue extends AbstractLocatable implements Statement {

    public Continue(int line, int column) {
        super(line, column);
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
