package ast.definition;

import ast.statement.Statement;
import ast.type.Type;

import java.util.List;

public class FunctionDefinition extends AbstractDefinition {

    private List<Statement> funcBody;

    public FunctionDefinition(int line, int column, String name, Type type, List<Statement> funcBody) {
        super(line, column,name, type);
        this.funcBody = funcBody;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(": ").append(getType()).append(" {\n");
        for (Statement stmt : funcBody) {
            sb.append("  ").append(stmt).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
