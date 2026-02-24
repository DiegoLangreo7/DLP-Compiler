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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(funcName).append("(");
        for (int i = 0; i < arguments.size(); i++) {
            sb.append(arguments.get(i));
            if (i < arguments.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
