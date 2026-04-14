package ast.type;

import ast.ASTNode;
import ast.Locatable;

import java.util.List;

public interface Type extends ASTNode {

    void mustBeLogical(Locatable locatable);
    Type arithmetic(Type type, Locatable locatable);
    Type arithmetic(Locatable locatable);
    Type comparison(Type type, Locatable locatable);
    Type logic(Type type, Locatable locatable);
    Type logic(Locatable locatable);
    void mustPromotesTo(Type type, Locatable locatable);
    void mustBeBuiltIn(Locatable locatable);
    Type squareBrackets(Type type, Locatable locatable);
    Type dot(String field, Locatable locatable);
    Type parenthesis(List<Type> parameters, Locatable locatable);
    Type canBeCastTo(Type type, Locatable locatable);
    int getNumberOfBytes();
    String suffix();

}
