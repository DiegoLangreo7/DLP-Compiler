package ast.type;

import ast.Locatable;

import java.util.List;

public abstract class AbstractType implements Type{

    @Override
    public void mustBeLogical(Locatable locatable) {
        new ErrorType("Logical type expected", locatable);
    }

    @Override
    public Type arithmetic(Type type, Locatable locatable) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType("An arithmetic operation cannot be done for types " + type + " and " + this, locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return new ErrorType("An unary minus operation cannot be done for type " + this, locatable);
    }

    @Override
    public Type comparison(Type type, Locatable locatable) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType("A comparison operation cannot be done for types " + type + " and " + this, locatable);
    }

    @Override
    public Type logic(Type type, Locatable locatable) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType("A logical operation cannot be done for types " + type + " and " + this, locatable);
    }

    @Override
    public Type logic(Locatable locatable) {
        return new ErrorType("An unary not operation cannot be done for type " + this, locatable);
    }

    @Override
    public void mustPromotesTo(Type type, Locatable locatable) {
        if (type instanceof ErrorType)
            return;
        new ErrorType("Type " + this + " cannot promotes to type " + type, locatable);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        new ErrorType("A simple type was required and I got a " + this + " type", locatable);
    }

    @Override
    public Type squareBrackets(Type type, Locatable locatable) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType("An array access operation cannot be done for type " + this, locatable);
    }

    @Override
    public Type dot(String field, Locatable locatable) {
        return new ErrorType("A struct access operation cannot be done for type " + this, locatable);
    }

    @Override
    public Type parenthesis(List<Type> parameters, Locatable locatable) {
        for(Type param : parameters){
            if(param instanceof ErrorType)
                return param;
        }
        return new ErrorType("An invocation operation cannot be done for type " + this, locatable);
    }

    @Override
    public Type canBeCastTo(Type type, Locatable locatable) {
        if (type instanceof ErrorType)
            return type;
        return new  ErrorType("Type " + this + " cannot be cast to " + type, locatable);
    }

    @Override
    public int getNumberOfBytes() {
        throw new UnsupportedOperationException("Error de sintesis");
    }
}
