package ast.expression;

import ast.Locatable;
import ast.type.Type;

public interface Expression extends Locatable {

    Boolean getLValue();

    void setLValue(Boolean LValue);

    void setType(Type type);

    Type getType();
}
