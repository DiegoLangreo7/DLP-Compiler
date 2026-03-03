package ast.type;

import ast.Locatable;

public class ErrorType implements Type {

    private String message;
    private Locatable location;

    public ErrorType( String message, Locatable locatable ){

        this.message = message;
        this.location = locatable;

    }

    @Override
    public String toString() {
        return "Exception in thread main : '" + location + "' at line " + location;
    }

}
