package ast.type;

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

}
