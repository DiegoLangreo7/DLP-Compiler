package ast.type;

import ast.Locatable;
import errorhandler.ErrorHandler;
import visitor.Visitor;

import java.util.List;

public class ErrorType extends AbstractType {

    private String message;
    private Locatable location;

    public ErrorType( String message, Locatable locatable ){

        this.message = message;
        this.location = locatable;
        ErrorHandler.getInstance().addError(this);

    }

    public int getLine() {
        return location.getLine();
    }

    public int getColumn() {
        return location.getColumn();
    }

    @Override
    public String toString() {
        return "Exception in thread main: '" + message + "' at line " + location.getLine() + " and column " + location.getColumn();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public void mustBeLogical(Locatable locatable) {
        // ErrorType absorbe validaciones para evitar errores en cascada
    }

    @Override
    public Type arithmetic(Type type, Locatable locatable) {
        return this;
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type comparison(Type type, Locatable locatable) {
        return this;
    }

    @Override
    public Type logic(Type type, Locatable locatable) {
        return this;
    }

    @Override
    public Type logic(Locatable locatable) {
        return this;
    }

    @Override
    public void mustPromotesTo(Type type, Locatable locatable) {
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        // No generar más errores si ya estamos en ErrorType
    }

    @Override
    public Type squareBrackets(Type type, Locatable locatable) {
        return this;
    }

    @Override
    public Type dot(String field, Locatable locatable) {
        return this;
    }

    @Override
    public Type parenthesis(List<Type> parameters, Locatable locatable) {
        return this;
    }

    @Override
    public Type canBeCastTo(Type type, Locatable locatable) {
        return this;
    }

}
