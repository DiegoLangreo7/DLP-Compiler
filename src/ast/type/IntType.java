package ast.type;

import ast.Locatable;
import visitor.Visitor;

public class IntType extends AbstractType{

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

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public void mustBeLogical(Locatable locatable) {
        // DO NOTHING
    }

    @Override
    public Type arithmetic(Type type, Locatable locatable) {
        if (this == type || type == CharType.getInstance())
            return this;
        else if(type == NumberType.getInstance())
            return type;
        return super.arithmetic(type, locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type comparison(Type type, Locatable locatable) {
        if (this == type)
            return this;
        return super.comparison(type, locatable);
    }

    @Override
    public Type logic(Type type, Locatable locatable) {
        if (this == type)
            return type;
        return super.logic(type, locatable);
    }

    @Override
    public Type logic(Locatable locatable) {
        return this;
    }

    @Override
    public void mustPromotesTo(Type type, Locatable locatable) {
        if (type == NumberType.getInstance() || type == this)
            return;
        super.mustPromotesTo(type, locatable);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        // DO NOTHING
    }

    @Override
    public Type canBeCastTo(Type type, Locatable locatable) {
        if (type == this || type == NumberType.getInstance() || type == CharType.getInstance())
            return type;
        return super.canBeCastTo(type, locatable);
    }
}
