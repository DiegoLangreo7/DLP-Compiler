package ast.type;

import visitor.Visitor;

public class IntType implements Type {

    private static IntType instance;

    private IntType(){}

    public static IntType getInstance() {
        if (instance == null) {
            instance = new IntType();
        }
        return instance;
    }

    public String toString() {
    	return "int";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
