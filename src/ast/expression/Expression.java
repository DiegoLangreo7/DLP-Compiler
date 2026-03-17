package ast.expression;

import ast.Locatable;

public interface Expression extends Locatable {

    Boolean getLValue();

    void setLValue(Boolean LValue);
}
