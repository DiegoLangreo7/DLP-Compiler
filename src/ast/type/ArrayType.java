package ast.type;

import visitor.Visitor;

public class ArrayType implements Type{

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
    	return "array["+size+"] of "+typeOf;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
