package ast.type;

import visitor.Visitor;

public class NumberType implements Type {

    private static NumberType instance;

    private NumberType(){}

    public static NumberType getInstance() {
        if (instance == null) {
            instance = new NumberType();
        }
        return instance;
    }

    public String toString() {
    	return "number";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return null;
    }
}
