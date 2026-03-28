package ast.type;

import visitor.Visitor;

public class VoidType extends AbstractType {

    private static VoidType instance;

    private VoidType(){}

    public static VoidType getInstance() {
        if (instance == null) {
            instance = new VoidType();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
