package ast.type;

import ast.Locatable;
import visitor.Visitor;

import java.util.List;

public class CharType extends AbstractType {

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

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type arithmetic(Type type, Locatable locatable) {
        if (this == type)
            return IntType.getInstance();
        return super.arithmetic(type, locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return IntType.getInstance();
    }

    @Override
    public Type comparison(Type type, Locatable locatable) {
        if (this == type)
            return IntType.getInstance();
        return super.comparison(type, locatable);
    }

    @Override
    public void mustPromotesTo(Type type, Locatable locatable) {
        if (type == this)
            return;
        super.mustPromotesTo(type, locatable);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        // DO NOTHING
    }

    @Override
    public Type canBeCastTo(Type type, Locatable locatable) {
        if (type == this || type == IntType.getInstance() || type == NumberType.getInstance())
            return type;
        return super.canBeCastTo(type, locatable);
    }
}
