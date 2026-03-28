package ast.type;

import ast.Locatable;
import visitor.Visitor;

public class ArrayType extends AbstractType {

    private int size;
    private Type typeOf;

    public ArrayType(int size, Type typeOf) {
        this.size = size;
        this.typeOf = typeOf;
    }

    public int getSize() {
        return size;
    }

    public Type getTypeOf() {
        return typeOf;
    }

    public String toString() {
    	return "array";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type squareBrackets(Type type, Locatable locatable){
        if(type == IntType.getInstance())
            return typeOf;
        return super.squareBrackets(type, locatable);
    }
}
