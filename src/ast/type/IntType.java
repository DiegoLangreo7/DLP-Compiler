package ast.type;

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

}
