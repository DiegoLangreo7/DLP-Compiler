package ast.definition;

import ast.type.Type;

public class VariableDefinition extends AbstractDefinition{

    public VariableDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }
}
