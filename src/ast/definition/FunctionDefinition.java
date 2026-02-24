package ast.definition;

import ast.statement.Statement;
import ast.type.Type;

import java.util.List;

public class FunctionDefinition extends AbstractDefinition {

    private List<VariableDefinition> varDefinitions;
    private List<Statement> statements;

    public FunctionDefinition(int line, int column, String name, Type type, List<VariableDefinition> varDefinitions, List<Statement> statements) {
        super(line, column,name, type);
        this.varDefinitions = varDefinitions;
        this.statements = statements;
    }

}
