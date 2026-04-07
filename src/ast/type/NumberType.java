package ast.type;

import ast.Locatable;
import visitor.Visitor;

public class NumberType extends AbstractType {

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

    @Override
    public Type arithmetic(Type type, Locatable locatable) {
        if (type == this || type == CharType.getInstance() || type == IntType.getInstance())
            return this;
        return super.arithmetic(type, locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return super.arithmetic(locatable);
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
        if(type == this || type == IntType.getInstance() || type == CharType.getInstance()){
            return type;
        }
        return super.canBeCastTo(type, locatable);
    }

    @Override
    public int getNumberOfBytes() {
        return 4;
    }
}
