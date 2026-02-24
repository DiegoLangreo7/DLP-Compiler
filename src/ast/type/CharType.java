package ast.type;

public class CharType implements Type {

    private static CharType instance;

    private CharType(){}

    public static CharType getInstance() {
        if (instance == null) {
            instance = new CharType();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "char";
    }
}
