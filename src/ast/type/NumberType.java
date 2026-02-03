package ast.type;

public class NumberType implements Type {

    private static NumberType instance;

    private NumberType(){}

    public static NumberType getInstance() {
        if (instance == null) {
            instance = new NumberType();
        }
        return instance;
    }

}
