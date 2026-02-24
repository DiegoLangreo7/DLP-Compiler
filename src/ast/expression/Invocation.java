package ast.expression;

import ast.AbstractLocatable;
import ast.statement.Statement;

import java.util.List;

public class Invocation extends AbstractLocatable implements Expression, Statement {

    private List<Expression> arguments;
    private Variable funcName;

    public Invocation(int line, int column, Variable funcName, List<Expression> arguments) {
        super(line, column);
        this.funcName = funcName;
        this.arguments = arguments;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public Variable getFuncName() {
        return funcName;
    }
}
