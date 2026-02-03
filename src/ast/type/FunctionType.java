package ast.type;

import ast.expression.Variable;

import java.util.List;

public class FunctionType implements Type {

    private Type returnType;
    private List<Variable> parameters;

    public FunctionType(Type returnType, List<Variable> parameters) {
        this.returnType = returnType;
        this.parameters = parameters;
    }

}
