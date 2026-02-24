package ast.definition;

import ast.AbstractLocatable;
import ast.type.Type;

public abstract class AbstractDefinition extends AbstractLocatable implements Definition {

    private String name;
    private Type type;

    public AbstractDefinition(int line, int column, String name, Type type) {
        super(line,column);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }


}
